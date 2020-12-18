package na.severinchik.iba_kotlin_lesson_14

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.installations.FirebaseInstallations
import na.severinchik.iba_kotlin_lesson_14.animationactivities.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseInstallations.getInstance().id.addOnSuccessListener {
            Log.d("TAG", "onCreate: $it")

        }


        val recyclerView = findViewById<View>(R.id.list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val anim = ScaleAnimation(
            0F, 1F, 0F, 1F,
            0.5F, recyclerView.measuredHeight.toFloat()
        )


        anim.duration = 4000
        anim.interpolator = DecelerateInterpolator()
        anim.fillAfter = true
        recyclerView.startAnimation(anim)

        var items: ArrayList<RocketAnimationItem> = arrayListOf<RocketAnimationItem>(
            RocketAnimationItem(
                getString(R.string.title_no_animation),
                Intent(this, NoAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_launch_rocket),
                Intent(this, LaunchRocketValueAnimatorAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_spin_rocket),
                Intent(this, RotateRocketAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_accelerate_rocket),
                Intent(this, AccelerateRocketAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_launch_rocket_objectanimator),
                Intent(this, LaunchRocketObjectAnimatorAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_color_animation),
                Intent(this, ColorAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.launch_spin),
                Intent(this, LaunchAndSpinAnimatorSetAnimatorActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.launch_spin_viewpropertyanimator),
                Intent(this, LaunchAndSpinViewPropertyAnimatorAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_with_doge),
                Intent(this, FlyWithDogeAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_animation_events),
                Intent(this, WithListenerAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_there_and_back),
                Intent(this, FlyThereAndBackAnimationActivity::class.java)
            ),
            RocketAnimationItem(
                getString(R.string.title_jump_and_blink),
                Intent(this, XmlAnimationActivity::class.java)
            )
        )

        recyclerView.adapter = RocketAdapter(this, items)
    }

    fun anotherActivity(view: View?) {
        startActivity(Intent(this, DrawableAnimActivity::class.java))
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left)
    }


    fun startFragmentActivity(view: View?) {
        startActivity(Intent(this, FragmentActivity::class.java))
    }

    fun startGestureActivity(view: View?) {
        startActivity(Intent(this, GestureActivity::class.java))
    }


}