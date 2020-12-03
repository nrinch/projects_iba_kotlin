package na.severinchik.iba_kotlin_lesson_10

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class MagicWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        Log.d("Worker:doWork", "start")
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }

        Log.d("Worker:doWork", "end")
        return Result.success()
    }
}

class MagicWorkerWithFail(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        Log.d("Worker:doWork2", "start")
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }

        Log.d("Worker:doWork2", "end")
        return Result.failure()
    }
}