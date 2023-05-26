package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.fieldview.Connection
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.NodeView

abstract class NodeViewConnectorInput(context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView){

}

class NodeViewConnectorInputData <T> (context: Context, nodeView: T, dataType: DataType) : NodeViewConnectorInput(context, nodeView) where T:NodeView, T:AbleToInput{
    private var permanentDataType = false
    init {
        this.dataType = dataType
        if(dataType != DataType.UNSPECIFIED) permanentDataType = true
    }
    fun tryToConnect(connectorOutputData: NodeViewConnectorOutputData<*>):Boolean{
        if(connectorOutputData.dataType == DataType.UNSPECIFIED && dataType == DataType.UNSPECIFIED) return true
        return (nodeView as AbleToInput).tryToConnect(this, connectorOutputData)
    }
    fun connect(connectorOutputData: NodeViewConnectorOutputData<*>, connection:Connection){
        if (this.connection != null) this.connection!!.delete()
        dataType = connectorOutputData.dataType
        this.connection = connection
        (nodeView as AbleToInput).connect(this, connectorOutputData)
        connectorOutputData.connection = connection
    }
    fun disconnect(){
        (nodeView as AbleToInput).disconnect(this)
        this.connection = null
        if(!permanentDataType) dataType = DataType.UNSPECIFIED
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
