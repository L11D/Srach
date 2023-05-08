package com.example.srach

import android.graphics.PointF
import android.util.Log
import android.view.ScaleGestureDetector

class SGListener(private val fieldView: FieldView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {


    override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
        val focusX = scaleGestureDetector.focusX
        val focusY = scaleGestureDetector.focusY
        val scale = scaleGestureDetector.scaleFactor

        if (fieldView.field.scale * scale in 0.3f..2f) {
            fieldView.field.scale *= scale
            fieldView.field.viewPosition *= scale
        }

       //Log.d("dddd", "SGListener: " + (fieldView.field.viewPosition * scale).x.toString() + ", " + (fieldView.field.viewPosition * scale).y.toString())

        return true
    }
}