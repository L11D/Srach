package com.example.srach

import android.graphics.Canvas

class NodeViewConnector(nodeView: NodeView) : Drawable, NodeViewUnit(nodeView) {

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawOval(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, paint)
    }
}