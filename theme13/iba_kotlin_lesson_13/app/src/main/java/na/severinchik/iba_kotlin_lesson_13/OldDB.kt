package na.severinchik.iba_kotlin_lesson_13

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class OldDB(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        const val DATABASE_NAME = "Notes.db"
        const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: OldDB? = null

        fun getInstance(context: Context): OldDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = OldDB(context)
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db.let {
            it?.execSQL(DatabaseInfo.SQL_CREATE_NOTES_TABLE_QUERY)
            it?.insert(DatabaseInfo.TableNotesInfo.TABLE_NOTES_NAME, null, getDefaultDBContent())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVerstion: Int, newVersion: Int) {
        if (oldVerstion < newVersion) {
            db.let {
                it?.execSQL(DatabaseInfo.SQL_DELETE_NOTES_TABLE_QUERY)
                onCreate(it)
            }
        }
    }

    fun getDefaultDBContent(): ContentValues {
        var contentValues = ContentValues()

        contentValues.put(
            DatabaseInfo.TableNotesInfo.COLUMN_NOTE_NAME,
            "Name One"
        )
        contentValues.put(
            DatabaseInfo.TableNotesInfo.COLUMN_NOTE_INFO,
            "Info One"
        )
        contentValues.put(
            DatabaseInfo.TableNotesInfo.COLUMN_NOTE_NAME,
            "Name Two"
        )
        contentValues.put(
            DatabaseInfo.TableNotesInfo.COLUMN_NOTE_INFO,
            "Info TWo"
        )
        return contentValues
    }
}


object DatabaseInfo {

    const val SQL_CREATE_NOTES_TABLE_QUERY = "CREATE TABLE ${TableNotesInfo.TABLE_NOTES_NAME} " +
            "(${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${TableNotesInfo.COLUMN_NOTE_NAME} TEXT," +
            "${TableNotesInfo.COLUMN_NOTE_INFO} TEXT)"

    const val SQL_DELETE_NOTES_TABLE_QUERY =
        "DROP TABLE IF EXISTS ${TableNotesInfo.TABLE_NOTES_NAME}"

    object TableNotesInfo : BaseColumns {
        const val TABLE_NOTES_NAME = "tableNotes"
        const val COLUMN_NOTE_NAME = "noteName"
        const val COLUMN_NOTE_INFO = "noteInfo"
    }
}