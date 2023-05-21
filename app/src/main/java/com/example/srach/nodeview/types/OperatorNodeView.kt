package com.example.srach.nodeview.types

import android.content.Context
import android.util.Log
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNode
import com.example.srach.interpretator.OperatorNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.DataTypes
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

class OperatorNodeView(context:Context, val operatorNode: OperatorNode, field: Field, position: Vector2f) :
    InOutAbleNodeView(context, field, position, 2, 1, DataTypes.INT) {

    override fun <I, O> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where O : NodeView, I : NodeView, I : AbleToInput, O : AbleToOutput {
        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = connectorOutput.getNodeOutput()
            Log.d("dddd", "connectLeft")
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.right = connectorOutput.getNodeOutput()
            Log.d("dddd", "connectRight")
        }
    }

    override fun unconnect(connectorInput: NodeViewConnectorInput) {
        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = null
            Log.d("dddd", "unconnectLeft")
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.right = null
            Log.d("dddd", "unconnectRight")
        }
    }

    override fun getNodeOutput(): MathNode {
        return operatorNode
    }
}