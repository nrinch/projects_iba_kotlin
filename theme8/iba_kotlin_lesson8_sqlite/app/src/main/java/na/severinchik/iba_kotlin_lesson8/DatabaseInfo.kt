package na.severinchik.iba_kotlin_lesson8

import android.provider.BaseColumns

object DatabaseInfo {

    const val SQL_CREATE_NOTES_TABLE_QUERY = "CREATE TABLE ${TableNotesInfo.TABLE_NOTES_NAME} " +
            "(${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${TableNotesInfo.COLUMN_NOTE_NAME} TEXT," +
            "${TableNotesInfo.COLUMN_NOTE_INFO} TEXT," +
            "${TableNotesInfo.COLUMN_TIME} TEXT)"

    const val SQL_DELETE_NOTES_TABLE_QUERY =
        "DROP TABLE IF EXISTS ${TableNotesInfo.TABLE_NOTES_NAME}"

    object TableNotesInfo : BaseColumns {
        const val TABLE_NOTES_NAME = "tableNotes"
        const val COLUMN_NOTE_NAME = "noteName"
        const val COLUMN_NOTE_INFO = "noteInfo"
        const val COLUMN_TIME = "noteDate"
    }
}