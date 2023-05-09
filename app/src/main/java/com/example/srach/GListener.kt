package com.example.srach

import android.content.Context
import android.graphics.Color
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class GListener(private val context: Context, private val fieldView: FieldView) : GestureDetector.OnGestureListener {
    var movableNodeView:NodeView? = null
        get() = field

    private val listenerLoop = ListenerLoop(this, fieldView)
    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
   private fun vibrate(){
       vibrator.vibrate(
           VibrationEffect.createOneShot(
               20,
               VibrationEffect.DEFAULT_AMPLITUDE
           ))
   }
    init {
        listenerLoop.start()
    }
    var lastTouchPos = Vector2f()
        get() = field

    fun baseActions(e: MotionEvent){
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchPos = Vector2f(e.x, e.y)
            }

            MotionEvent.ACTION_MOVE -> {
                if (movableNodeView != null) {
                    movableNodeView!!.position += (Vector2f(e.x, e.y) - lastTouchPos) * (1/fieldView.field.scale)
                }
                lastTouchPos = Vector2f(e.x, e.y)
            }

            MotionEvent.ACTION_UP -> {
                movableNodeView = null
            }
        }
    }

    override fun onDown(e: MotionEvent): Boolean {

        return true
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (movableNodeView == null) {
            fieldView.field.viewPosition += Vector2f(distanceX, distanceY)
        }
//        Log.d("dddd", "onScroll")
        //Log.d("dddd", fieldView.getField().getViewPosition().x.toString())
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        movableNodeView = fieldView.nodeViewsCollision(Vector2f(e.x, e.y))
        if (movableNodeView != null) {
            movableNodeView!!.colorN = Color.GREEN
            vibrate()
        }

        fieldView.invalidate()
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }
}