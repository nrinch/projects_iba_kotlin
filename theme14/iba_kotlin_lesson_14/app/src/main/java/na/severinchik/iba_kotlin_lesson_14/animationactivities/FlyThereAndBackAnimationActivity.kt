package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.ValueAnimator

class FlyThereAndBackAnimationActivity :BaseAnimationActivity(){
    override fun onStartAnimation() {
        val animator = ValueAnimator.ofFloat(0f, -mScreenHeight)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            mRocket.translationY = value
            mDoge.translationY = value
        }
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = 3
        animator.duration = 100L
        animator.start()
    }
}