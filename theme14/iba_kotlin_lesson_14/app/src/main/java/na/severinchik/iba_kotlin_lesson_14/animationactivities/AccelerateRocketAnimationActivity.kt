package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator

class AccelerateRocketAnimationActivity : BaseAnimationActivity() {

    override fun onStartAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, -mScreenHeight)
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            mRocket.setTranslationY(value)
        }
        valueAnimator.interpolator = AccelerateInterpolator(1.5f)
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION
        valueAnimator.start()
    }
}