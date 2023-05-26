package com.example.srach.fieldview

import android.view.GestureDetector
import android.view.MotionEvent

class DoubleTapListener(val fieldView: FieldView) : GestureDetector.SimpleOnGestureListener() {
    override fun onDoubleTap(e: MotionEvent): Boolean {
        fieldView.nodeViewsCollision(Vector2f(e.x, e.y))?.delete()
        return true
    }
}