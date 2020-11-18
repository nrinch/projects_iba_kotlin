package na.severinchik.iba_kotlin_lesson5

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager.*
import androidx.fragment.app.FragmentTransaction
import na.severinchik.iba_kotlin_lesson5.basics.BlankFragment
import na.severinchik.iba_kotlin_lesson5.basics.EmptyFragment
import na.severinchik.iba_kotlin_lesson5.basics.LifeCycleFragment

class MainActivity : AppCompatActivity() {
    lateinit var blankFragment: BlankFragment
    val TAG:String = "Lifecycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Activity")

        var trasaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        trasaction.add(R.id.container, LifeCycleFragment(), "LifecycleFragment")
        trasaction.add(R.id.container2, BlankFragment.newInstance("prm1","prm2"), "EmptyFragment")
        trasaction.commit()
        blankFragment = BlankFragment()


        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            var trasaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            trasaction.add(R.id.container2, blankFragment, "LifecycleFragment")
            trasaction.commit()
            }

//        startActivity(Intent(this,BottomNavActivity::class.java))
//        startActivity(Intent(this, DrawerActivity::class.java))
    }

    fun Replace(view: View) {
        var trasaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        trasaction.add(R.id.container2, BlankFragment(), "BlankFragment")
        trasaction.addToBackStack(null)
        trasaction.commit()

    }

    fun Remove(view: View) {
        var trasaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        trasaction.remove(blankFragment)
        trasaction.commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity")
    }
}