package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator

class RotateRocketAnimationActivity :BaseAnimationActivity(){
    override fun onStartAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            // 2
            mRocket.rotation = value
        }
        animator.interpolator = LinearInterpolator()
        animator.duration = DEFAULT_ANIMATION_DURATION
        animator.start()
    }
}