package na.severinchik.iba_kotlin_lesson_14

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class DrawableAnimActivity : AppCompatActivity() {
    lateinit var wifiAnimation: AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_anim)
        val imageView = findViewById<ImageView>(R.id.image_with_anim)
        imageView.setBackgroundResource(R.drawable.drawable_anim)
        wifiAnimation = imageView.background as AnimationDrawable

        findViewById<View>(R.id.root_view).setOnTouchListener(object : SwipeListener(this@DrawableAnimActivity) {
            override fun onSwipeLeft() {
                finish()
            }
        })
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        wifiAnimation.start()
        Log.d("TAG","")
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right)
    }
}