package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.example.srach.R
import com.example.srach.fieldview.Drawable
import com.example.srach.nodeview.NodeView

class NodeViewBody(context: Context, nodeView: NodeView) : Drawable, NodeViewUnit(context, nodeView) {

    var round = 20f
    private var displayRound = 0f

    var isActive = false
    override fun solveDisplayPosition(){
        super.solveDisplayPosition()
        displayRound = round * nodeView.field.scale
    }
    private val strokeWidth = 6f

    val strokePaint = Paint().apply {
        color = context.getColor(R.color.activeNodeStroke)
        style = Paint.Style.STROKE
    }
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        strokePaint.strokeWidth = strokeWidth * nodeView.field.scale
        canvas.drawRoundRect(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, displayRound, displayRound, paint)
        if(isActive){
            canvas.drawRoundRect(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, displayRound, displayRound, strokePaint)
        }
    }
}