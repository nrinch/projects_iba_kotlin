package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import na.severinchik.iba_kotlin_lesson_14.R

abstract class BaseAnimationActivity : AppCompatActivity() {

    val DEFAULT_ANIMATION_DURATION = 2500L
    lateinit var mRocket: View
    lateinit var mDoge: View
    lateinit var mFrameLayout: View
    protected var mScreenHeight = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_animation)
        mRocket = findViewById(R.id.rocket)
        mDoge = findViewById(R.id.doge)
        mFrameLayout = findViewById(R.id.container)
        mFrameLayout.setOnClickListener { onStartAnimation() }
    }

    override fun onResume() {
        super.onResume()
        val displaymetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymetrics)
        mScreenHeight = displaymetrics.heightPixels.toFloat()
    }

    protected abstract fun onStartAnimation()
}