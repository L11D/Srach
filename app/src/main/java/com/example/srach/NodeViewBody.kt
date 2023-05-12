package com.example.srach

import android.graphics.Canvas
import android.graphics.Paint

class NodeViewBody(nodeView: NodeView) : Drawable, NodeViewUnit(nodeView) {

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