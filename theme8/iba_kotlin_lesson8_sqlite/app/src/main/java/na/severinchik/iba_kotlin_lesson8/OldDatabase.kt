package na.severinchik.iba_kotlin_lesson8

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.concurrent.atomic.AtomicBoolean

class OldDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        const val DATABASE_NAME = "Notes.db"
        const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: OldDatabase? = null

        fun getInstance(context: Context): OldDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = OldDatabase(context)
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db.let {
            it?.execSQL(DatabaseInfo.SQL_CREATE_NOTES_TABLE_QUERY)
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
}