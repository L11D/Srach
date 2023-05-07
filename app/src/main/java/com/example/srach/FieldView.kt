package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.core.view.GestureDetectorCompat
import kotlin.math.log

private var mScaleFactor = 1f

private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        mScaleFactor *= detector.scaleFactor

        //Log.d("dddd", mScaleFactor.toString())
        return true
    }
}

class FieldView(context: Context, attrs: AttributeSet?) : View(context, attrs){

    private var field = Field().apply { setViewPosition(Vector2f(0f, 0f)) }
    private var gListener = GListener(this)
    private var gestureDetectorCompat = GestureDetectorCompat(this.context, gListener)
    private val mScaleDetector = ScaleGestureDetector(this.context, scaleListener)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = 500 //govno
        val desiredHeight = 500

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> Math.min(desiredWidth, widthSize)
            else -> desiredWidth
        }
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        field.setViewSize(Vector2i(width, height))
    }

    private var lastPos = Vector2f(0f, 0f)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchPos = Vector2f(event.x, event.y)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastPos = touchPos
                //return true
            }

            MotionEvent.ACTION_MOVE -> {
                field.addViewPosition(touchPos - lastPos)
                lastPos=touchPos
            }

            MotionEvent.ACTION_UP -> {
            }
        }
        invalidate()
        mScaleDetector.onTouchEvent(event);
        gestureDetectorCompat.onTouchEvent(event)

        return true
        //return super.onTouchEvent(event)
    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        gestureDetectorCompat.onTouchEvent(event)
//        invalidate()
//        return super.onTouchEvent(event)
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.draw(canvas)
    }
}