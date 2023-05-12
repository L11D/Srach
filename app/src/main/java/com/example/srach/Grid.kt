package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Grid(private val context: Context, private val field: Field):Drawable {
    private val cellSize = 50f
    private val cellHideThreshold = 50f
    private val paintGrid = Paint().apply { color = context.getColor(R.color.grid) }
    private val paintBigger = Paint().apply { color = context.getColor(R.color.gridBigger) }
    private val biggerSizeInCells = 5
    private var displayPosition = Vector2f()
    private var displayCellSize = 0f
    private var displayCellBiggerSize = 0f

    private fun solveDisplayPosition(){
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        displayCellSize = cellSize * field.scale
        displayCellBiggerSize = displayCellSize * biggerSizeInCells

        displayPosition.x = (- viewPos.x + viewSize.x/2)
        displayPosition.y = (- viewPos.y + viewSize.y/2)
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        paintGrid.alpha = if(displayCellSize > cellHideThreshold) 255 else ((displayCellSize/cellHideThreshold) * 255).toInt()

            val verticalLinesCount = (field.viewSize.x/displayCellSize).toInt()
            for (i in 0..verticalLinesCount) {
                canvas.drawLine(
                    displayPosition.x % displayCellSize + displayCellSize * i,
                    0f,
                    displayPosition.x % displayCellSize + displayCellSize * i,
                    field.viewSize.y.toFloat(),
                    paintGrid
                )
            }
            val horizontalLinesCount = (field.viewSize.y/displayCellSize).toInt()
            for (i in 0..horizontalLinesCount) {
                canvas.drawLine(
                    0f,
                    displayPosition.y % displayCellSize + displayCellSize * i,
                    field.viewSize.x.toFloat(),
                    displayPosition.y % displayCellSize + displayCellSize * i,
                    paintGrid
                )
            }

        val verticalLinesBiggerCount = (field.viewSize.x/(displayCellBiggerSize)).toInt()
        for (i in 0..verticalLinesBiggerCount) {
            canvas.drawLine(
                displayPosition.x % displayCellBiggerSize + displayCellBiggerSize * i,
                0f,
                displayPosition.x % displayCellBiggerSize+ displayCellBiggerSize * i,
                field.viewSize.y.toFloat(),
                paintBigger
            )
        }
        val horizontalLinesBiggerCount = (field.viewSize.y/(displayCellBiggerSize)).toInt()
        for (i in 0..horizontalLinesBiggerCount) {
            canvas.drawLine(
                0f,
                displayPosition.y % displayCellBiggerSize + displayCellBiggerSize * i,
                field.viewSize.x.toFloat(),
                displayPosition.y % displayCellBiggerSize + displayCellBiggerSize * i,
                paintBigger
            )
        }
    }

}