package com.example.srach

import android.graphics.Canvas
import android.graphics.Paint

abstract class NodeViewUnit(protected val nodeView: NodeView) : Drawable {
    var position = Vector2f()
        get() = field
        set(value) {
            field = value
        }
    var size = Vector2f(10f, 10f)
        get() = field
        set(value) {
            field = value
        }
    var paint = Paint()
        get() = field
        set(value) {field = value}


    protected var displayPosition = Vector2f()
    protected var displaySize = Vector2f()

    protected open fun solveDisplayPosition() {
        displayPosition = nodeView.displayPosition + position * nodeView.field.scale
        displaySize = size * nodeView.field.scale
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
    }
}