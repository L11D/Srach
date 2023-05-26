package com.example.srach.fieldview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.srach.NewConsole
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import java.io.Console

class FieldView(context: Context, attrs: AttributeSet?) : View(context, attrs){

    var field = Field(this.context)

    private var gListener = GListener(context,this)
    private var gestureDetector = GestureDetector(this.context, gListener)

    private val scaleGestureListener = SGListener(this)
    private val scaleDetector = ScaleGestureDetector(this.context, scaleGestureListener)

    private val doubleTapListener = DoubleTapListener(this)
    private val doubleTapDetector = GestureDetector(this.context, doubleTapListener)

    fun bindConsole(console:NewConsole){
        field.console = console
    }

    fun nodeViewsCollision(pos: Vector2f): NodeView?{
        return field.nodeViewsCollision(pos)
    }

    fun connectorsCollision(pos: Vector2f): NodeViewConnector?{
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

    val runLoop = RunLoop(field)

    fun run(){
        //field.run()
        runLoop.start()
    }

    fun createNodeUserInput(layout:ViewGroup){
        layout.removeAllViews()
        if (field.activeNodeView is AbleToUserInput){
            (field.activeNodeView as AbleToUserInput).createUserInput(layout)
        }
    }


    fun addNode(nodeName: String){
        field.addNode(nodeName)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        gListener.baseActions(event)
        doubleTapDetector.onTouchEvent(event)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.draw(canvas)
    }

}