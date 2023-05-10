package com.example.srach

import android.graphics.Canvas

class NodeViewConnector private constructor(nodeView: NodeView) : Drawable, NodeViewUnit(nodeView) {
    constructor(nodeView: NodeView, nodeOutput: NodeOutput):this(nodeView){
        this.nodeOutput = nodeOutput
    }
    constructor(nodeView: NodeView, nodeInput: NodeInput):this(nodeView){
        this.nodeInput = nodeInput
    }

    var nodeOutput:NodeOutput? = null
        get() = field
    var nodeInput:NodeInput? = null
        get() = field

    var globalPosition = Vector2f()
        get() = field

    fun collision(pos:Vector2f):Boolean{
        return pos.x in displayPosition.x..(displayPosition.x+displaySize.x)&&
                pos.y in displayPosition.y..(displayPosition.y+displaySize.y)
    }

    override fun solveDisplayPosition() {
        super.solveDisplayPosition()
        globalPosition = position + nodeView.position
        globalPosition.x += size.x/2
        globalPosition.y += size.y/2
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawOval(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, paint)
    }
}