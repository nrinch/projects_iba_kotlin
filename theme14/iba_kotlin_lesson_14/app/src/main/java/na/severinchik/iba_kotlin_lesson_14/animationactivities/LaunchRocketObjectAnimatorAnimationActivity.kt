package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.ObjectAnimator

class LaunchRocketObjectAnimatorAnimationActivity :BaseAnimationActivity() {
    override fun onStartAnimation() {
        val objectAnimator: ObjectAnimator = ObjectAnimator.ofFloat(
            mRocket, "translationY",
            0f, -mScreenHeight
        )
        objectAnimator.duration = DEFAULT_ANIMATION_DURATION
        objectAnimator.start()
    }
}