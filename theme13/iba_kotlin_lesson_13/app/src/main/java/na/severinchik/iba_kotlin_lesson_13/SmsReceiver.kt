package na.severinchik.iba_kotlin_lesson_13

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class SmsReceiver : BroadcastReceiver() {

    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    private var smsIntent: Intent? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent!!.extras

        if (intent!!.action != null && intent!!.action.equals(SMS_RECEIVED, true)) {
            try {
                if (bundle != null) {
                    val pdusObj = bundle["pdus"] as Array<Any>?
                    for (i in pdusObj!!.indices) {

//                        val currentMessage: SmsMessage =
//                            getIncomingMessage(pdusObj!![i] as ByteArray, bundle)

                        val currentMessage: SmsMessage =
                            SmsMessage.createFromPdu(pdusObj!![i] as ByteArray, bundle.getString("format"))

                        val phoneNumber: String = currentMessage.displayOriginatingAddress
                        val time: Long = currentMessage.timestampMillis
                        val message: String = currentMessage.displayMessageBody
                        smsIntent = Intent("read").also {
                            it.putExtra("message", message)
                            it.putExtra("sender", phoneNumber)
                            it.putExtra("time", time)
                        }
                        LocalBroadcastManager.getInstance(context!!).sendBroadcast(smsIntent!!)

                    }
                }
            } catch (e: Exception) {
                Log.e("SMSReader", "Exception$e")
            }
        }
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        currentSMS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else {
            SmsMessage.createFromPdu(aObject as ByteArray)
        }
        return currentSMS
    }
}