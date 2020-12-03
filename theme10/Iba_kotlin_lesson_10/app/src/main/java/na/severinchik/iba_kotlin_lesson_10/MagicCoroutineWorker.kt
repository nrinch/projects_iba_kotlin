package na.severinchik.iba_kotlin_lesson_10

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.math.log

class MagicCoroutineWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 1L
    }

    override suspend fun doWork(): Result = coroutineScope {
        val firstUpdate = workDataOf(Progress to 0)
        setProgress(firstUpdate)
        delay(100)
        val lastUpdate = workDataOf(Progress to 100)
        Log.d("Workre","delay")
        setProgress(lastUpdate)
        delay(100)
        return@coroutineScope Result.success()
    }
}