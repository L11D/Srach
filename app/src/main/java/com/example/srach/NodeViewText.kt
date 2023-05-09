package com.example.srach

import android.graphics.Canvas
import android.graphics.Typeface

class NodeViewText(nodeView: NodeView) : Drawable, NodeViewUnit(nodeView) {

    var textSize = 30f
        get() = field
        set(value) {field = value}

    private var displayTextSize = 0f

    var text = "lorem ipsun"
        get() = field
        set(value) {field = value}


    init {
        paint.typeface = Typeface.DEFAULT
        paint.textSize = textSize
    }

    override fun solveDisplayPosition() {
        displayPosition = nodeView.displayPosition + position * nodeView.field.scale
        displayTextSize = textSize * nodeView.field.scale
        paint.textSize = displayTextSize
        displaySize = Vector2f(paint.measureText(text), displayTextSize)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawText(text, displayPosition.x, displayPosition.y + displayTextSize, paint)
    }
}