package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.util.Log

class Field() : Drawable{
    var viewSize = Vector2i()
        get() = field
        set(value) {field = value}

    var viewPosition = Vector2f()
        get() = field
        set(value) {field = value}
    var scale = 1f
        get() = field
        set(value) {
            field = value
        }

    private val nodeViewList = mutableListOf<NodeView>()

    init {
        nodeViewList.add(NodeView(this, Vector2f(0f, 0f)))
        nodeViewList.add(NodeView(this, Vector2f(100f, 300f)))
        nodeViewList.add(NodeView(this, Vector2f(-100f, 300f)))
        nodeViewList.add(NodeView(this, Vector2f(0f, 500f)))
        nodeViewList.add(NodeView(this, Vector2f(0f, 1000f)))
        nodeViewList.add(NodeView(this, Vector2f(200f, 1000f)))
        nodeViewList.add(NodeView(this, Vector2f(-200f, 1200f)))
        nodeViewList[0].colorN = Color.BLACK
    }

    override fun draw(canvas:Canvas){
        canvas.drawColor(Color.GRAY)
        for (node in nodeViewList)
        {
            node.draw(canvas)
        }
    }
}