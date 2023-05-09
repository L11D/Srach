package com.example.srach

import android.graphics.Canvas
import android.graphics.Typeface

class NodeViewText(nodeView: NodeView) : Drawable, NodeViewUnit(nodeView) {

    var textSize = 30f
        get() = field
        set(value) {field = value}

    var text = "lorem ipsun"
        get() = field
        set(value) {field = value}


    init {
        paint.typeface = Typeface.DEFAULT
        paint.textSize = textSize
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawOval(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, paint)
    }
}