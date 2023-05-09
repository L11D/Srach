package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.shapes.RoundRectShape
import android.util.Log

class NodeView(val node:Node, private val field:Field, var position: Vector2f) : Drawable {
    var colorN = Color.BLUE
        get() = field
        set(value) {field = value}

    private var size = Vector2f(100f, 100f)
    private var displayPosition = Vector2f()
    private var displaySize = Vector2f()

    private var body = RoundRectShape(null, null, null)

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
            canvas.drawRect(displayPosition.x, displayPosition.y, right, bottom, Paint().apply { this.color = colorN })
        }

    }
}