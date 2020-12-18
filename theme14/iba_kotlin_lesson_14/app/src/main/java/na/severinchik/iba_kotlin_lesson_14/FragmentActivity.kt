package na.severinchik.iba_kotlin_lesson_14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

//        val fragmentManager: FragmentManager = supportFragmentManager
//
//        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//      //Animations
//        transaction.setCustomAnimations(
//            R.anim.enter,
//            R.anim.exit,
//            R.anim.pop_enter,
//            R.anim.pop_exit
//        )
//        transaction.replace(R.id.nav_host_fragment, FirstFragment())
//        transaction.addToBackStack(null)
//        transaction.commit()

    }
}