package na.severinchik.iba_kotlin_lesson_111

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ServiceResult() :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if(intent.action!!.equals("na.severinchik.iba_kotlin_lesson_111")) {

        }
    }
}