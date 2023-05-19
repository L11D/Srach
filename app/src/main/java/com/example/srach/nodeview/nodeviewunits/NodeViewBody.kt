package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.fieldview.Drawable
import com.example.srach.nodeview.NodeView

class NodeViewBody(context: Context, nodeView: NodeView) : Drawable, NodeViewUnit(context, nodeView) {

    var round = 20f
        get() = field
        set(value) {field = value}
    private var displayRound = 0f

    override fun solveDisplayPosition(){
        super.solveDisplayPosition()
        displayRound = round * nodeView.field.scale
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawRoundRect(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, displayRound, displayRound, paint)
    }
}