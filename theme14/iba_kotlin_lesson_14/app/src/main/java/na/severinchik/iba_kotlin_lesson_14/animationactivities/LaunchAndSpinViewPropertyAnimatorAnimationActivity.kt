package na.severinchik.iba_kotlin_lesson_14.animationactivities

class LaunchAndSpinViewPropertyAnimatorAnimationActivity :BaseAnimationActivity(){
    override fun onStartAnimation() {
        mRocket.animate().translationY(-mScreenHeight)
            .rotationBy(360f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .start()
    }
}