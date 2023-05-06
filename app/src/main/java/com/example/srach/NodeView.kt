package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class NodeView(private val field:Field, private val position: Vector2f) : Drawable {


    private fun solveDisplayPosition():Vector2f{
        val viewPos = field.getViewPosition()
        val viewSize = field.getViewSize()
        val pos = Vector2f(0f ,0f)
        pos.x = position.x + viewPos.x + viewSize.x/2
        pos.y = position.y + viewPos.y + viewSize.y/2
        return pos
    }

    override fun draw(canvas: Canvas) {
        var displayPosition = solveDisplayPosition()
        val size = 50f
        canvas.drawRect(displayPosition.x, displayPosition.y, displayPosition.x+size, displayPosition.y +size, Paint().apply { color=Color.BLUE })
    }
}