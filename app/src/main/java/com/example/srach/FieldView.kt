package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class FieldView(context: Context, attrs: AttributeSet?) : View(context, attrs){

    private lateinit var field: Field

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

        field = Field(Vector2i(width, height), Vector2f(0f, 0f))
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
                Log.d("dddd", "down")

            }

            MotionEvent.ACTION_MOVE -> {
                //field.addViewPosition(lastPos- touchPos)
                Log.d("dddd", "move")
                field.addViewPosition(Vector2f(1f, 1f))
            }

            MotionEvent.ACTION_UP -> {
                Log.d("dddd", "up")

            }
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.draw(canvas)
    }
}