package com.example.srach

import android.graphics.Canvas
import android.graphics.Color

class Field(private var viewSize:Vector2i, private var viewPosition:Vector2f) : Drawable{
    private val nodeViewList = mutableListOf<NodeView>()

    init {
        nodeViewList.add(NodeView(this, Vector2f(0f, 0f)))
        nodeViewList.add(NodeView(this, Vector2f(100f, 300f)))
    }

    fun setViewSize(viewSize: Vector2i){
        this.viewSize = viewSize
    }
    fun getViewSize():Vector2i{
        return viewSize
    }

    fun setViewPosition(viewPosition: Vector2f) {
        this.viewPosition = viewPosition
    }
    fun addViewPosition(deltaPosition: Vector2f){
        viewPosition + deltaPosition
    }
    fun getViewPosition():Vector2f {
        return viewPosition
    }

    override fun draw(canvas:Canvas){
        canvas.drawColor(Color.GRAY)
        for (node in nodeViewList)
        {
            node.draw(canvas)
        }
    }
}