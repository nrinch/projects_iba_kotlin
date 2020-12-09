package na.severinchik.iba_kotlin_lesson_111

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log


class SampleService : Service() {
    

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Service ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: Service")
        var intent = Intent("na.severinchik.iba_kotlin_lesson_111")
        intent.putExtra("KEY","VALUE")
        sendBroadcast(intent)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Service")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind: Service")
        
        return null
    }

    inner class LocalBinder : Binder() {
        fun getService(): SampleService = this@SampleService
    }
}