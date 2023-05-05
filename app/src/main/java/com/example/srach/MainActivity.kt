package com.example.srach

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.*
import android.graphics.text.MeasuredText
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.Math.max
import java.lang.Math.min

class CustomDrawableView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val body: ShapeDrawable
    private val connector: ShapeDrawable

    init {
        val width = 118
        val height = 50
        contentDescription = "context.resources.getString(R.string.my_view_desc)"

        body = ShapeDrawable(RectShape())

        body.paint.color = 0xff74AC23.toInt()
        body.setBounds(0, 0, width, height)
    }

    init {
        connector = ShapeDrawable(OvalShape())
        connector.paint.color = 0xff74AC23.toInt()
        connector.setBounds(100, 80, 110, 90)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 500
        val desiredHeight = 500

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth
        }
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }
        setMeasuredDimension(width, height)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                //Log.d("dddd", event.x.toString())
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

    private var p = Paint();
    private var p1 = Paint();
    private var str = "HELL"

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        body.draw(canvas)
        connector.draw(canvas)
        p1.color = Color.WHITE;
        canvas.drawRect(Rect(0, 0, 30, 100), p1)
        p.color = Color.BLUE;
        p.typeface = Typeface.DEFAULT;
        p.textSize = 50F;
        Log.d("dddd", p.measureText(str).toString())
        canvas.drawText(str, 0F, p.textSize, p);
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
    }
}

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

//        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
//
//        val params = ConstraintLayout.LayoutParams(
//            ConstraintLayout.LayoutParams.WRAP_CONTENT,
//            ConstraintLayout.LayoutParams.WRAP_CONTENT
//        )
//        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
//        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
//        params.marginStart = -50
//        params.topMargin = 500
//
//        val myView = CustomDrawableView(this, null);
//        myView.layoutParams = params
//        constraintLayout.addView(myView);

        val canwasDraw = CanvasDraw(this, null)
        setContentView(canwasDraw)
        val catCanvas = CatCanvasView(this)
        //setContentView(catCanvas)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("dddd", "dfdf")

        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("dddd", event.x.toString())
                    return true
                }

                MotionEvent.ACTION_MOVE -> {
                    //Log.e("gggg", "MainNNNNNmove")
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    return true
                }
            }
        }
        return super.onTouchEvent(event)

    }
}