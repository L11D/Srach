package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class NodeView(private val field:Field, private val position: Vector2f) : Drawable {

    var colorN = Color.BLUE
        get() = field
        set(value) {field = value}

    private var size = 50f
    private var displayPosition = Vector2f(0f, 0f)
    private var displaySize = 0f
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
        canvas.drawRect(displayPosition.x, displayPosition.y, displayPosition.x + displaySize, displayPosition.y + displaySize, Paint().apply { this.color = colorN })
    }
}