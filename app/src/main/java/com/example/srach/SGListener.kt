package com.example.srach

import android.graphics.PointF
import android.util.Log
import android.view.ScaleGestureDetector

class SGListener(private val fieldView: FieldView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {


    override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
        val focusX = scaleGestureDetector.focusX
        val focusY = scaleGestureDetector.focusY
        val scale = scaleGestureDetector.scaleFactor

        fieldView.field.scale *= scale
        val viewPos = fieldView.field.viewPosition
        fieldView.field.viewPosition = Vector2f(viewPos.x * scale, viewPos.y * scale)



        //Log.d("dddd", "focus: " + focusX.toString() + ", " + focusY.toString())

        return true
    }
}