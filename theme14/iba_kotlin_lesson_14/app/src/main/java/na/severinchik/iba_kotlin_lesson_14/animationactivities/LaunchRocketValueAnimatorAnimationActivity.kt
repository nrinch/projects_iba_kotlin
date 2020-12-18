package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator

class LaunchRocketValueAnimatorAnimationActivity :BaseAnimationActivity() {

    override fun onStartAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, -mScreenHeight)
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            mRocket.translationY = value
        }
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION
        valueAnimator.start()
    }

}
