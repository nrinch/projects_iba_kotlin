package na.severinchik.iba_kotlin_lesson_10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.lifecycle.Observer
import androidx.work.*
import na.severinchik.iba_kotlin_lesson_10.MagicCoroutineWorker.Companion.Progress
import java.util.concurrent.TimeUnit


const val CHANNEL_ID = "2133"
const val CHANNEL_ID_SECOND = "01"
const val NOTIFICATION_ID = "0"
const val EXTRA_TEXT_REPLY = "RES"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationWithActionReply()
//            someSampleNotification()
//        notificationWithPendingIntent()
//    sampleNotificationWithBigText()
//        sampleNotificationWithBigImage()
//        sampleNotificationWithAnotherStyle()
//            notificationWithProgress()

//        startWorker()
//        startWorkerPeriodicly() //!!
//        startManyWorkers()
//        startCoroutineWorker()
//        startWorkerWithData()
    }

    fun startWorker() {
        var work = OneTimeWorkRequest.Builder(MagicWorker::class.java)
            .addTag("firstWork")
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(this).enqueue(work)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work.id).observe(this,
            { t -> Log.d("Worker", "onChanged: ${t!!.state} ") })
    }

    fun startWorkerPeriodicly() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        var periodicWork =
            PeriodicWorkRequest.Builder(
                MagicWorker::class.java,
                15,
                TimeUnit.MINUTES,
                5,
                TimeUnit.MINUTES
            )
//                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "periodicWork",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWork
        )
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(periodicWork.id).observe(this,
            { t -> Log.d("Worker", "onChan: ${t!!.state}") })

    }

    fun startManyWorkers() {
        var work1 = OneTimeWorkRequest.Builder(MagicWorkerWithFail::class.java).build()
        var work2 = OneTimeWorkRequest.Builder(MagicWorker::class.java).build()
        var work3 = OneTimeWorkRequest.Builder(MagicWorker::class.java).build()

//        WorkManager.getInstance(this).enqueue(mutableListOf(work1, work2, work3))

        WorkManager.getInstance(this)
            .beginWith(work1)
            .then(work2)
            .then(work3)
            .enqueue()
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work1.id).observe(this, { t ->
            Log.d("Worker", "${t!!.state}")
        })
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work2.id).observe(this, { t ->
            Log.d("Worker", "${t!!.state}")
        })
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work3.id).observe(this, { t ->
            Log.d("Worker", "${t!!.state}")
        })
    }

    fun startCoroutineWorker() {
        var magicCoroutineWorker =
            OneTimeWorkRequest.Builder(MagicCoroutineWorker::class.java).build()
        WorkManager.getInstance(this).enqueue(magicCoroutineWorker)
        WorkManager.getInstance(this)
            // requestId is the WorkRequest id
            .getWorkInfoByIdLiveData(magicCoroutineWorker.id)
            .observe(this, { workInfo: WorkInfo? ->
                if (workInfo != null) {
                    var progress = workInfo.progress
                    var value = progress.getInt(Progress, -1)
                    Log.d("Worker", "Progress $value")
                }
            })
    }

    fun startWorkerWithData() {
        val data: Data = workDataOf(KEY to 42)
//        val work = OneTimeWorkRequest.Builder(DataWorker::class.java)
        val work = OneTimeWorkRequestBuilder<DataWorker>()
            .setInputData(data)
            .build()
        WorkManager.getInstance(this).enqueue(work)


        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work.id)
            .observe(this, Observer { info ->
                if (info != null && info.state.isFinished) {
                    val result = info.outputData.getInt(RESULT_KEY, 0)
                    Log.d("Worker", "$result")
                }
            })
    }


    fun sampleNotification() {
        var notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setChannelId(CHANNEL_ID)
            .build()

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(1, notification)

    }

    fun someSampleNotification() {
        var firstNotification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample 1")
            .setContentText("I notify u")
            .setColor(getColor(R.color.black))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentInfo("First!")
            .setChannelId(CHANNEL_ID)
            .build()
        var thirdNotification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample 3")
            .setContentText("I notify u")
            .setColor(getColor(R.color.black))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentInfo("First!")
            .setChannelId(CHANNEL_ID)
            .build()


        var secondNotification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample 2")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setChannelId(CHANNEL_ID_SECOND)
            .setOngoing(true)
            .build()

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)
        val mChannelSecond =
            NotificationChannel(
                CHANNEL_ID_SECOND,
                "my_channel2",
                NotificationManager.IMPORTANCE_HIGH
            )
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.createNotificationChannel(mChannelSecond)
        notificationManager.notify(1, firstNotification)
        notificationManager.notify(3, thirdNotification)
        notificationManager.notify(2, secondNotification)
    }

    fun notificationWithPendingIntent() {
        var notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, NotificationActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setAutoCancel(true)
            .setNumber(42)
            .build()

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(1, notification)
    }

    fun notificationWithProgress() {
        var workerProgressNotification =
            OneTimeWorkRequest.Builder(WorkerProgressNotification::class.java).build()
        WorkManager.getInstance(this).enqueue(workerProgressNotification)
    }

    fun sampleNotificationWithBigText() {
        var notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "Come and join our lunchtime yoga class with experienced yoga teacher Divya Bridge!\n" +
                            "When? Every Tuesday at 1.30 p.m.\n" +
                            "Where? Meeting Room 7\n" +
                            "How much? £10 for four 30-minute classes.\n" +
                            "What to bring? Comfortable clothes. Divya will provide the yoga mats.\n" +
                            "How to join? Write to Sam at Sam.Holden@example.com\n" +
                            "We can only take a maximum of 20 in the room, so book now!"
                )
            )
            .build()

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(1, notification)
    }

    fun sampleNotificationWithBigImage() {
        var notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigPictureStyle().bigLargeIcon(
                    BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.bobr
                    )
                )
            )
            .build()

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(3, notification)
    }

    fun sampleNotificationWithAnotherStyle() {
        var notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)

            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(

//                NotificationCompat.MessagingStyle("You")
//                    .setConversationTitle("Android chat")
//                    .addMessage("Всем привет!", System.currentTimeMillis(), "Ivan")
//                    .addMessage(
//                        "Кто перешел на новую студию, как оно?",
//                        System.currentTimeMillis(),
//                        "Ivan"
//                    )
//                    .addMessage(
//                        "Я пока не переходил, жду отзывов",
//                        System.currentTimeMillis(),
//                        "Andrey"
//                    )


                NotificationCompat.InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
                    .addLine("Line 3")
                    .setBigContentTitle("Extended title")
                    .setSummaryText("+5 more")
            )
            .build()

        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(1, notification)
    }

    fun notificationWithActionReply() {
        val remoteInput = RemoteInput.Builder(EXTRA_TEXT_REPLY)
            .setLabel("Type message")
            .build()

        val action = NotificationCompat.Action.Builder(
            android.R.drawable.ic_menu_send,
            "Reply",
            PendingIntent.getActivity(
                this,
                0,
                Intent(this, NotificationActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        ).addRemoteInput(remoteInput).build()

        var notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Sample")
            .setContentText("I notify u")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(action)
            .build()
        val mChannel =
            NotificationChannel(CHANNEL_ID, "my_channel", NotificationManager.IMPORTANCE_HIGH)
        mChannel.description = "SampleChannel"
        mChannel.enableLights(true)

        var notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        notificationManager.notify(1, notification)
    }
}