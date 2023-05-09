package com.example.srach

import android.graphics.Color
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class GListener(private val fieldView: FieldView) : GestureDetector.OnGestureListener {
    private var movableNodeView:NodeView? = null
    private val listenerLoop = ListenerLoop(this, fieldView)
    var lastTouchPos = Vector2f()
        get() = field
    var touched = false
        get() = field

    fun baseActions(e: MotionEvent){
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchPos = Vector2f(e.x, e.y)
                touched = true
            }

            MotionEvent.ACTION_MOVE -> {
                if (movableNodeView != null) {
                    movableNodeView!!.position += (Vector2f(e.x, e.y) - lastTouchPos) * (1/fieldView.field.scale)
                }
                lastTouchPos = Vector2f(e.x, e.y)
                if(!listenerLoop.isAlive){
                    listenerLoop.start()
                }
            }

            MotionEvent.ACTION_UP -> {
                movableNodeView = null
                touched = false
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