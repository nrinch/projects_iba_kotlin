package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.Animator
import android.animation.ValueAnimator
import android.widget.Toast

class WithListenerAnimationActivity :BaseAnimationActivity(){

    override fun onStartAnimation() {
        val animator = ValueAnimator.ofFloat(0f, -mScreenHeight)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            mRocket.translationY = value
            mDoge.translationY = value
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // 3
                Toast.makeText(
                    this@WithListenerAnimationActivity,
                    "Doge took off",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onAnimationEnd(animation: Animator) {
                // 4
                Toast.makeText(
                    this@WithListenerAnimationActivity,
                    "Doge is on the moon",
                    Toast.LENGTH_SHORT
                )
                    .show()
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        animator.duration = 5000L
        animator.start()
    }

}