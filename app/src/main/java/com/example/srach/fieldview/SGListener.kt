package com.example.srach.fieldview

import android.view.ScaleGestureDetector

class SGListener(private val fieldView: FieldView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {


    override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
        val focusX = scaleGestureDetector.focusX
        val focusY = scaleGestureDetector.focusY
        val scale = scaleGestureDetector.scaleFactor

        if (fieldView.field.scale * scale in 0.2f..2f) {
            fieldView.field.scale *= scale
            fieldView.field.viewPosition *= scale
        }

       //Log.d("dddd", "SGListener: " + (fieldView.field.viewPosition * scale).x.toString() + ", " + (fieldView.field.viewPosition * scale).y.toString())

        return true
    }
}