package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator

class LaunchAndSpinAnimatorSetAnimatorActivity : BaseAnimationActivity(){
    override fun onStartAnimation() {
        val positionAnimator = ValueAnimator.ofFloat(0f, -mScreenHeight)
        positionAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            mRocket.translationY = value
        }
        val rotationAnimator: ObjectAnimator = ObjectAnimator.ofFloat(mRocket, "rotation", 0f, 180f)
        val animatorSet = AnimatorSet()
        animatorSet.play(positionAnimator).with(rotationAnimator)
        animatorSet.duration = DEFAULT_ANIMATION_DURATION
        animatorSet.start()
    }
}
