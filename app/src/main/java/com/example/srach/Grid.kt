package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Grid(private val context: Context, private val field: Field):Drawable {
    private val cellSize = 50f
    private val paint = Paint().apply { color = context.getColor(R.color.grid) }
    private var displayPosition = Vector2f()
    private var displayCellSize = 0f

    private fun solveDisplayPosition(){
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        displayCellSize = cellSize * field.scale * ((1 / field.scale) / 5)

        displayPosition.x = (- viewPos.x + viewSize.x/2) % displayCellSize
        displayPosition.y = (- viewPos.y + viewSize.y/2) % displayCellSize
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        val verticalLinesCount = (field.viewSize.x/displayCellSize).toInt()
        for (i in 0..verticalLinesCount) {
            canvas.drawLine(
                displayPosition.x + displayCellSize * i,
                0f,
                displayPosition.x + displayCellSize * i,
                field.viewSize.y.toFloat(),
                paint
            )
        }
        val horizontalLinesCount = (field.viewSize.y/displayCellSize).toInt()
        for (i in 0..horizontalLinesCount) {
            canvas.drawLine(
                0f,
                displayPosition.y + displayCellSize * i,
                field.viewSize.x.toFloat(),
                displayPosition.y + displayCellSize * i,
                paint
            )
        }
    }

}