package com.example.srach

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class CanvasDraw(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if(::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888 )
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(Color.BLUE)
        extraCanvas.drawColor(Color.BLACK)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("dddd", "move")
                extraCanvas.drawColor(Color.BLUE)
                invalidate()

                return true
            }

            MotionEvent.ACTION_MOVE -> {
                //Log.d("dddd", "move")
                return true
            }

            MotionEvent.ACTION_UP -> {
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0F, 0F, null)
    }
}