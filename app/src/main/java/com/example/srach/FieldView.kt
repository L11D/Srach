package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
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
    fun getField():Field{ return field}
    private var gListener = GListener(this)
    private var gestureDetector = GestureDetector(this.context, gListener)

    private val mScaleGestureListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        /**
         * This is the active focal point in terms of the viewport. Could be a local
         * variable but kept here to minimize per-frame allocations.
         */
        private val viewportFocus = PointF()
        private var lastSpanX: Float = 0f
        private var lastSpanY: Float = 0f

        // Detects that new pointers are going down.
        override fun onScaleBegin(scaleGestureDetector: ScaleGestureDetector): Boolean {
            lastSpanX = scaleGestureDetector.currentSpanX
            lastSpanY = scaleGestureDetector.currentSpanY
            return true
        }

        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            val spanX: Float = scaleGestureDetector.currentSpanX
            val spanY: Float = scaleGestureDetector.currentSpanY

            Log.d("dddd", "span: " + spanX.toString() + ", " + spanY.toString())

            val newWidth: Float = lastSpanX / spanX * field.getViewSize().x
            val newHeight: Float = lastSpanY / spanY * field.getViewSize().y

            Log.d("dddd", "size: " + newWidth.toString() + ", " + newHeight.toString())

            val focusX: Float = scaleGestureDetector.focusX
            val focusY: Float = scaleGestureDetector.focusY

            Log.d("dddd", "focus: " + focusX.toString() + ", " + focusY.toString())


            // Makes sure that the chart point is within the chart region.
            // See the sample for the implementation of hitTest().
            //hitTest(focusX, focusY, viewportFocus)



//            mContentRect?.apply {
//                mCurrentViewport.set(
//                    viewportFocus.x - newWidth * (focusX - left) / width(),
//                    viewportFocus.y - newHeight * (bottom - focusY) / height(),
//                    0f,
//                    0f
//                )
//            }

            //Log.d("dddd", newWidth.toString() + "   " + newHeight.toString())

            //field.addViewPosition(Vector2f(newWidth, newHeight))

            // Invalidates the View to update the display.
            //ViewCompat.postInvalidateOnAnimation(this@InteractiveLineGraphView)

            lastSpanX = spanX
            lastSpanY = spanY
            return true
        }
    }

    private val mScaleDetector = ScaleGestureDetector(this.context, mScaleGestureListener)


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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mScaleDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event)

        invalidate()

        return true
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.draw(canvas)
    }

}