package com.example.srach.nodeview.types

import android.content.Context
import android.util.Log
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.CoolNumberNode
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.MathNode
import com.example.srach.interpretor.OperatorNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

abstract class OperatorNodeView(context:Context, val operatorNode: OperatorNode, field: Field, position: Vector2f) :
    InOutAbleNodeView(context, field, position, 2, 1, DataType.UNSPECIFIED) {

    override fun <I, O> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where O : NodeView, I : NodeView, I : AbleToInput, O : AbleToOutput {
        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = connectorOutput.getNodeOutput()
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.right = connectorOutput.getNodeOutput()
        }
//        if(inputConnectorsList[0].dataType == DataType.UNSPECIFIED && inputConnectorsList[1].dataType == DataType.UNSPECIFIED){
//
//        }
//        else if (inputConnectorsList[0].dataType == DataType.UNSPECIFIED){
//            operatorNode.left = CoolNumberNode(connectorOutput.dataType)
//            outputConnectorsList[0].dataType = operatorNode.evaluate().type
//            operatorNode.left = null
//        }
//        else if (inputConnectorsList[1].dataType == DataType.UNSPECIFIED) {
//            operatorNode.right = CoolNumberNode(connectorOutput.dataType)
//            outputConnectorsList[0].dataType = operatorNode.evaluate().type
//            operatorNode.right = null
//        }
//        else{
//            try{
//                outputConnectorsList[0].dataType = operatorNode.evaluate().type
//            }catch (e:Throwable){}
//        }
        if (operatorNode.left == null && operatorNode.right == null){

        }
        else if (operatorNode.left == null){
            operatorNode.left = CoolNumberNode(connectorOutput.dataType)
            outputConnectorsList[0].dataType = operatorNode.evaluate().type
            operatorNode.left = null
        }
        else if (operatorNode.right == null) {
            operatorNode.right = CoolNumberNode(connectorOutput.dataType)
            outputConnectorsList[0].dataType = operatorNode.evaluate().type
            operatorNode.right = null
        }
        else{
            outputConnectorsList[0].dataType = operatorNode.evaluate().type
        }
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = null
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.right = null
        }
    }

    override fun getNodeOutput(): MathNode {
        return operatorNode
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        var ans = true

        if (operatorNode.left == null && operatorNode.right == null){
            operatorNode.left = CoolNumberNode(connectorOutput.dataType)
            operatorNode.right = CoolNumberNode(connectorOutput.dataType)
            try {
                operatorNode.evaluate()
            }catch (e:Throwable){
                ans = false
            }
            operatorNode.left = null
            operatorNode.right = null
        }else if (operatorNode.left == null){
            operatorNode.left = CoolNumberNode(connectorOutput.dataType)
            try {
                operatorNode.evaluate()
            }catch (e:Throwable){
                ans = false
            }
            operatorNode.left = null
        }else if (operatorNode.right == null){
            operatorNode.right = CoolNumberNode(connectorOutput.dataType)
            try {
                operatorNode.evaluate()
            }catch (e:Throwable){
                ans = false
            }
            operatorNode.right = null
        }

        return ans
    }
}