package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import kotlin.math.log
import kotlin.math.sqrt

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

    private var curvePath: Path = Path()
    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = 5f // default: Hairline-width (really thin)
    }
    private val pathMeasure = PathMeasure()

    init {
        curvePath.moveTo(0f, 1000f)
        curvePath.cubicTo(250f, 1000f, 250f, 500f, 500f, 500f)
        pathMeasure.setPath(curvePath, false)


    }
    private fun distanceBetween(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        val dx = x1 - x2
        val dy = y1 - y2
        return sqrt(dx * dx + dy * dy)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                val x = event.x
                val y = event.y
                val distanceThreshold = 20f

                val pointCoords = floatArrayOf(0f, 0f)
                var distance = Float.MAX_VALUE
                var position = 0f
                var step = 1f // шаг для определения ближайшей точки на кривой

                while (position <= pathMeasure.length) {
                    pathMeasure.getPosTan(position, pointCoords, null)
                    val dist = distanceBetween(x, y, pointCoords[0], pointCoords[1])
                    if (dist < distance) {
                        distance = dist
                    }
                    position += step
                }


                if (distance < distanceThreshold) {
                    Log.d("dddd", "curvePress")
                } else {
                    // Нажатие было не на кривую
                }
            }
        }

        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        gListener.baseActions(event)

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        field.draw(canvas)

        //canvas.drawPath(curvePath, paint)
    }

}