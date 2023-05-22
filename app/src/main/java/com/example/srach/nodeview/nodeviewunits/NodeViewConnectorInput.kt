package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.LogicNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.NodeView

abstract class NodeViewConnectorInput(context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView){

}

class NodeViewConnectorInputData <T> (context: Context, nodeView: T, dataType: DataType) : NodeViewConnectorInput(context, nodeView) where T:NodeView, T:AbleToInput{
    init {
        this.dataType = dataType
    }
    fun connect(connectorOutputData: NodeViewConnectorOutputData<*>){
        (nodeView as AbleToInput).connect(this, connectorOutputData)
    }
    fun unconnect(){
        (nodeView as AbleToInput).unconnect(this)
    }
}
class NodeViewConnectorInputExec <T> (context: Context, nodeView: T) : NodeViewConnectorInput(context, nodeView) where T:NodeView, T:AbleToExec{

    fun getNode(): LogicNode {
        return (nodeView as AbleToExec).getExecNode()
    }

    init {
        dataType = DataType.EXEC
    }
    var round = 8f
        get() = field
        set(value) {field = value}
    private var displayRound = 0f
    override fun solveDisplayPosition(){
        super.solveDisplayPosition()
        displayRound = round * nodeView.field.scale
    }
    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        canvas.drawRoundRect(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, displayRound, displayRound, paint)
    }
}
