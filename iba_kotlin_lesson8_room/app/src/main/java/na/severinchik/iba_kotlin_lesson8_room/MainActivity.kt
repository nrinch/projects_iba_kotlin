package na.severinchik.iba_kotlin_lesson8_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.contracts.contract

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        test {
//            Log.d("TAG", ": ")
//            go()
//        }

    }

//    fun <T> test(block: Test.()->T) {
//        contract {
//            callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
//        }
//        return
//    }

}
//
//interface Test {
//
//    fun go()
//}

