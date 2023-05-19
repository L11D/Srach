package com.example.srach.nodeview

import android.graphics.Canvas
import com.example.srach.fieldview.Drawable
import com.example.srach.fieldview.Vector2f

class NodeViewConnectorInput private constructor(nodeView: NodeView) : NodeViewConnector(nodeView){
    lateinit var inputableNode:AbleToInput
    constructor(inputableNodeView: InputableNodeView, NULL:Int) : this(inputableNodeView){
        inputableNode = inputableNodeView
    }
    constructor(inOutAbleNodeView: InOutAbleNodeView, NULL: Int) : this(inOutAbleNodeView){
        inputableNode = inOutAbleNodeView
    }
}

class NodeViewConnectorOutput private constructor(nodeView: NodeView) : NodeViewConnector(nodeView){
    lateinit var outputableNode:AbleToOutput
    constructor(outputableNodeView: OutputableNodeView, NULL:Int) : this(outputableNodeView){
        outputableNode = outputableNodeView
    }
    constructor(inOutAbleNodeView: InOutAbleNodeView, NULL: Int) : this(inOutAbleNodeView){
        outputableNode = inOutAbleNodeView
    }
}

abstract class NodeViewConnector (nodeView: NodeView) : Drawable, NodeViewUnit(nodeView) {
//    constructor(nodeView: NodeView, nodeOutput: NodeOutput):this(nodeView){
//        this.nodeOutput = nodeOutput
//    }
//    constructor(nodeView: NodeView, nodeInput: NodeInput):this(nodeView){
//        this.nodeInput = nodeInput
//    }
//    var nodeOutput: NodeOutput? = null
//        get() = field
//    var nodeInput: NodeInput? = null
//        get() = field


    var globalPosition = Vector2f()
        get() = field

    fun collision(pos: Vector2f):Boolean{
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