package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.MathNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.NodeView

abstract class NodeViewConnectorOutput(context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView) {

}
class NodeViewConnectorOutputData<T> (context: Context, nodeView: T, dataType: DataType) : NodeViewConnectorOutput(context, nodeView) where T:NodeView, T: AbleToOutput {
    init {
        this.dataType = dataType
    }
    fun getNodeOutput():MathNode{
        return (nodeView as AbleToOutput).getNodeOutput()
    }
}
class NodeViewConnectorOutputExec <T> (context: Context, nodeView: T) : NodeViewConnectorOutput(context, nodeView) where T:NodeView, T: AbleToExec {
    fun connect(connectorInputData: NodeViewConnectorInputExec<*>){
        (nodeView as AbleToExec).connectExec(connectorInputData, this)
    }
    fun unconnect(){
        (nodeView as AbleToExec).unconnectExec()
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
