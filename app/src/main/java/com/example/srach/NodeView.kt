package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log

class NodeView(private val field:Field, private val position: Vector2f) : Drawable {

    var colorN = Color.BLUE
        get() = field
        set(value) {field = value}

    private var size = Vector2f(50f, 50f)
    private var displayPosition = Vector2f()
    private var displaySize = Vector2f()
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