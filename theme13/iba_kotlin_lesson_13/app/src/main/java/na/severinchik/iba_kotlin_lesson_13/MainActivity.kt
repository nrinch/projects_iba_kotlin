package na.severinchik.iba_kotlin_lesson_13

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.BatteryManager
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import na.severinchik.iba_kotlin_lesson_13.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

private const val someEventName = "na.severinchik.iba_kotlin_lesson_13.SOME_EVENT"
private const val REQUEST_CODE = 101
const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action.equals("read", ignoreCase = true)) {
                val message = intent.getStringExtra("message")
                val sender = intent.getStringExtra("sender")
                val time = intent.getLongExtra("time", 0)
                Log.d(TAG, "onReceive")
                binding.sender.text = sender
                binding.time.text = SimpleDateFormat("MM/YY HH:mm").format(time)
                binding.message.text = message
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkSelfPermission(
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS),
                REQUEST_CODE
            )
            return
        }
        val filter = IntentFilter()
        filter.addAction("android.provider.Telephony.SMS_RECEIVED")

        registerReceiver(SmsReceiver(), filter, Manifest.permission.RECEIVE_SMS, null)

        val myServiceIntent = Intent(this, SmsForefroundReceiver::class.java)
        ContextCompat.startForegroundService(this, myServiceIntent)

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("read"));

        binding.refreshBatteryStatus.setOnClickListener {

            val batteryStatus: Intent? =
                IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
                    registerReceiver(null, ifilter)
                }
            val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
            val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL
            binding.batteryStatus.text = "Is charging $isCharging"
        }

        val uri = Uri.parse("content://na.severinchik.iba_kotlin_lesson_13.PROVIDER")

        val contentResolver = contentResolver

        var cursor = contentResolver.query(uri, null, null, null, null)
        while (cursor!!.moveToNext()) {
            var uid = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            var name =
                cursor.getString(cursor.getColumnIndex(DatabaseInfo.TableNotesInfo.COLUMN_NOTE_NAME))
            var info =
                cursor.getString(cursor.getColumnIndex(DatabaseInfo.TableNotesInfo.COLUMN_NOTE_INFO))
            Log.d(TAG, "onCreate: id $uid | name: $name | info: $info")
        }


        val eventFilter = IntentFilter()
        eventFilter.addAction(someEventName)
        registerReceiver(eventReceiver, eventFilter)

        binding.list.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getSMS()!!)
        binding.list.setOnItemClickListener { parent, view, position, id ->
            sendBroadcast(Intent(someEventName))
        }
    }


    private val eventReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "onEventReceiver: clicked")
        }
    }

    fun getSMS(): Array<String>? {
        val sms: MutableList<String> = ArrayList()
        val uriSMSURI = Uri.parse("content://sms/inbox")
        val cur: Cursor? = contentResolver.query(uriSMSURI, null, null, null, null)
        while (cur != null && cur.moveToNext()) {
            val address: String = cur.getString(cur.getColumnIndex("address"))
            val body: String = cur.getString(cur.getColumnIndexOrThrow("body"))
            sms.add("Number: $address .Message: $body")
        }
        if (cur != null) {
            cur.close()
        }

        return sms.toTypedArray()
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(receiver)
        unregisterReceiver(eventReceiver)
    }
}