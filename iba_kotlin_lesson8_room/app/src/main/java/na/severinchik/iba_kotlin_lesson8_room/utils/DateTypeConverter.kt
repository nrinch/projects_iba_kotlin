package na.severinchik.iba_kotlin_lesson8_room.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun convertFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertToDate(timestamp: Long): Date {
        return Date(timestamp)
    }

    fun convertToMonthYear(timestamp: Long): String {
        val format = SimpleDateFormat("MM/YY")
        return format.format(timestamp)
    }


}