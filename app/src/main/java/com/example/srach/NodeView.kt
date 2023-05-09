package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RoundRectShape
import android.util.Log
import kotlin.math.roundToInt

class NodeView(val node:Node, val field:Field, var position: Vector2f) : Drawable {
    var colorN = Color.BLUE
        get() = field
        set(value) {field = value}

    private var size = Vector2f(100f, 100f)
    var displayPosition = Vector2f()
        get() = field
    private var displaySize = Vector2f()

    val body = NodeViewBody(this).apply {
        position =  Vector2f(0f, 0f)
        this@apply.size = this@NodeView.size
        paint.color = colorN
    }
    val connector = NodeViewConnector(this).apply {
        position = Vector2f(20f, 20f)
        size = Vector2f(20f, 20f)
        paint.color = Color.RED
    }

    fun collision(pos:Vector2f):Boolean{
        return pos.x in displayPosition.x..(displayPosition.x+displaySize.x)&&
                pos.y in displayPosition.y..(displayPosition.y+displaySize.y)
    }
    private fun solveDisplayPosition(){
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        val scale = field.scale

        displayPosition.x = position.x * scale - viewPos.x + viewSize.x/2
        displayPosition.y = position.y * scale - viewPos.y + viewSize.y/2
        displaySize = size * scale
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        val right = displayPosition.x + displaySize.x
        val bottom = displayPosition.y + displaySize.y

        if (displayPosition.x < field.viewSize.x && right >= 0 &&
            displayPosition.y < field.viewSize.y && bottom >= 0){
            body.draw(canvas)
            connector.draw(canvas)
        }
    }
}