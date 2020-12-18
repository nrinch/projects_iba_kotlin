package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator

class FlyWithDogeAnimationActivity : BaseAnimationActivity(){
    override fun onStartAnimation() {
        val positionAnimator = ValueAnimator.ofFloat(0f, -mScreenHeight)
        positionAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            // You can use value to set properties of many objects
            mRocket.translationY = value
            mDoge.translationY = value
        }
        val rotationAnimator = ValueAnimator.ofFloat(0f, 360f)
        rotationAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            mDoge.rotation = value
        }
//        rotationAnimator.interpolator = AccelerateInterpolator(2f)
        val animatorSet = AnimatorSet()
        animatorSet.play(positionAnimator).with(rotationAnimator)
        animatorSet.interpolator =AccelerateInterpolator(2f)
        animatorSet.duration = 5000L
        animatorSet.start()
    }
}