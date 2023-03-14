package com.example.appnhikhoa.model

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.appnhikhoa.R

class TranslateAnimation(context: Context,viewAnimation: View) : View.OnTouchListener{
    var gestureDetector : GestureDetector = GestureDetector(context,SimpleGestureDetector(viewAnimation))

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(p1!!)
    }

    class SimpleGestureDetector(viewAnimation: View) : GestureDetector.SimpleOnGestureListener() {
        var viewAnimation : View = viewAnimation
        var isFinishAnimation : Boolean = true

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if(distanceY > 0){
                hiddenView()
            }else{
                showView()
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        private fun showView() {
            if(viewAnimation == null || viewAnimation.visibility == View.VISIBLE){
                return
            }

            val animationUp : Animation = AnimationUtils.loadAnimation(viewAnimation.context, R.anim.move_up)
            animationUp.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {
                    viewAnimation.visibility = View.VISIBLE
                    isFinishAnimation = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    isFinishAnimation = true
                }

                override fun onAnimationRepeat(p0: Animation?) {
                    TODO("Not yet implemented")
                }

            })

            if(isFinishAnimation){
                viewAnimation.startAnimation(animationUp)
            }
        }

        private fun hiddenView() {
            if(viewAnimation == null || viewAnimation.visibility == View.GONE){
                return
            }

            val animationDown : Animation = AnimationUtils.loadAnimation(viewAnimation.context, R.anim.move_down)
            animationDown.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {
                    viewAnimation.visibility = View.VISIBLE
                    isFinishAnimation = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    viewAnimation.visibility = View.GONE
                    isFinishAnimation = true
                }

                override fun onAnimationRepeat(p0: Animation?) {
                    TODO("Not yet implemented")
                }

            })

            if(isFinishAnimation){
                viewAnimation.startAnimation(animationDown)
            }
        }
    }
}