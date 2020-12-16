package na.severinchik.iba_kotlin_lesson_13

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class AppContentProvider : ContentProvider() {
    lateinit var db: OldDB
    override fun onCreate(): Boolean {
        db = OldDB.getInstance(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.readableDatabase.query(
            DatabaseInfo.TableNotesInfo.TABLE_NOTES_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}