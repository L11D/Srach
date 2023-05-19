package com.example.srach.nodeview.types

import android.content.Context
import android.util.Log
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.interpretator.OperatorNodeInt
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput

class OperatorNodeView(context:Context, val operatorNodeInt: OperatorNodeInt, field: Field, position: Vector2f) :
    InOutAbleNodeView(context, field, position, 2, 1) {

    override fun connect(
        connectorInput: NodeViewConnectorInput,
        connectorOutput: NodeViewConnectorOutput
    ) {
        if (inputConnectorsList.indexOf(connectorInput) == 0) {
            operatorNodeInt.left = connectorOutput.outputableNode.getNodeOutput()
            Log.d("dddd", "connectLeft")
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNodeInt.right = connectorOutput.outputableNode.getNodeOutput()
            Log.d("dddd", "connectRight")
        }
    }
    override fun unconnect(connectorInput: NodeViewConnectorInput) {
        if (inputConnectorsList.indexOf(connectorInput) == 0) {
            operatorNodeInt.left = null
            Log.d("dddd", "unconnectLeft")
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNodeInt.right = null
            Log.d("dddd", "unconnectRight")
        }
    }

    override fun getNodeOutput(): MathNodeInt {
        return operatorNodeInt
    }

    fun print(){
        Log.d("dddd", operatorNodeInt.evaluate().toString())
    }


}