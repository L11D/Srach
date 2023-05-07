package com.example.srach

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class GListener(private val fieldView: FieldView) : GestureDetector.OnGestureListener {
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
        fieldView.getField().addViewPosition(Vector2f(-distanceX, -distanceY));
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        //Log.d("dddd", "onLongPress")

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