package na.severinchik.iba_kotlin_lesson_10

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

const val KEY = "key"
const val RESULT_KEY = "result"

class DataWorker (context : Context, params : WorkerParameters)
    : Worker(context, params)  {

    override fun doWork(): Result {
        val value = inputData.getInt(KEY, 0)

        val result = value +42

           val output: Data = workDataOf(RESULT_KEY to result)

        return Result.success(output)
    }
}