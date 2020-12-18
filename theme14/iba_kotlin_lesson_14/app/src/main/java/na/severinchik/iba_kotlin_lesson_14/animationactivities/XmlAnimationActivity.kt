package na.severinchik.iba_kotlin_lesson_14.animationactivities

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import na.severinchik.iba_kotlin_lesson_14.R

class XmlAnimationActivity :BaseAnimationActivity(){
    override fun onStartAnimation() {
// 1
        val rocketAnimatorSet =
            AnimatorInflater.loadAnimator(this, R.animator.jump_and_blink) as AnimatorSet
        // 2
        rocketAnimatorSet.setTarget(mRocket)

        // 3
        val dogeAnimatorSet =
            AnimatorInflater.loadAnimator(this, R.animator.jump_and_blink) as AnimatorSet
        // 4
        dogeAnimatorSet.setTarget(mDoge)

        // 5
        val bothAnimatorSet = AnimatorSet()
        bothAnimatorSet.playTogether(rocketAnimatorSet, dogeAnimatorSet)
        // 6
        bothAnimatorSet.duration = DEFAULT_ANIMATION_DURATION
        bothAnimatorSet.start()
    }
}