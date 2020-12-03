package na.severinchik.iba_kotlin_lesson_10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class WorkerProgressNotification(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 500L
    }

    override suspend fun doWork(): Result = coroutineScope {

        var notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setProgress(100, 0, true)

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(1, notification.build())

//        for (i in 0..100 step 10) {
//            delay(delayDuration)
//
//            notification.setProgress(100, i, false)
//            notification.setContentText("Progress $i %")
//            notificationManager.notify(1, notification.build())
//        }
//        notificationManager.cancel(1)
        return@coroutineScope Result.success()
    }
}