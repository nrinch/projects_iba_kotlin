package na.severinchik.iba_kotlin_lesson_13

import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.Message
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.text.SimpleDateFormat


class SmsForefroundReceiver : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action.equals("read", ignoreCase = true)) {
                val message = intent.getStringExtra("message")
                val sender = intent.getStringExtra("sender")
                val time = intent.getLongExtra("time", 0)
                Log.d(TAG, "onReceive")
                createNotification(sender!!, message!!)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("read"));
        createNotification("", "")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotification(sender: String, message: String) {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                ""
            }
        val customView = RemoteViews(packageName, R.layout.custom_notify_view)
        val customBigView = RemoteViews(packageName, R.layout.custom_notify_big_view)
        customView.setTextViewText(R.id.notification_title, sender)
        customView.setTextViewText(R.id.content_notification,message)
        customView.setOnClickPendingIntent(R.id.action_notification,pendingIntent)

        customBigView.setTextViewText(R.id.notification_title, sender)
        customBigView.setTextViewText(R.id.content_notification,message)
        customBigView.setOnClickPendingIntent(R.id.action_notification,pendingIntent)


        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("I see you (-_-) $sender")
//            .setContentText(message)
//            .setContentIntent(pendingIntent)
//            .setContent(customView)
            .setSmallIcon(R.mipmap.ic_launcher)


//            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(customView)
            .setCustomBigContentView(customBigView)
            .build()
        startForeground(101, notification)

    }

    private fun stringMethodCustomView():String{
        return "stringMethod"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}