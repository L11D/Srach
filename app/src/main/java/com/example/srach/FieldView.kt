package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

class FieldView(context: Context, attrs: AttributeSet?) : View(context, attrs){

    var field = Field(this.context)
        get() = field
        set(value) {field = value}

    private var gListener = GListener(context,this)
    private var gestureDetector = GestureDetector(this.context, gListener)
    private val scaleGestureListener = SGListener(this)
    private val scaleDetector = ScaleGestureDetector(this.context, scaleGestureListener)

    fun nodeViewsCollision(pos:Vector2f):NodeView?{
        for(node in field.nodeViewList){
            if (node.collision(pos)) return node
        }
        return null
    }

    fun connectorsCollision(pos: Vector2f):NodeViewConnector?{
        for(node in field.nodeViewList) {
            val out = node.connectorCollision(pos)
            if( out != null) return out
        }
        return null
    }

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
        field.viewSize = Vector2i(width, height)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        gListener.baseActions(event)

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.draw(canvas)
    }

}