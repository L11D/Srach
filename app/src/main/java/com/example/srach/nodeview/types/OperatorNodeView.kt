package com.example.srach.nodeview.types

import android.content.Context
import android.util.Log
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.Data
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.ValueNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.interpretor.operators.OperatorNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

abstract class OperatorNodeView(
    context: Context,
    val operatorNode: OperatorNode,
    field: Field,
    position: Vector2f
) :
    InOutAbleNodeView(context, field, position, 2, 1, DataType.UNSPECIFIED) {

    var leftDataType: DataType? = null
    var rightDataType: DataType? = null

    override fun <I, O> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where O : NodeView, I : NodeView, I : AbleToInput, O : AbleToOutput {

        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = connectorOutput.getNodeOutput()
            if (connectorOutput.dataType != DataType.UNSPECIFIED)
                leftDataType = connectorOutput.dataType
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.right = connectorOutput.getNodeOutput()
            if (connectorOutput.dataType != DataType.UNSPECIFIED)
                rightDataType = connectorOutput.dataType
        }

        solveOutputType()
    }

    private fun solveOutputType() {
        if (leftDataType != null || rightDataType != null) {
            val tempLeft = operatorNode.left
            val tempRight = operatorNode.right

            operatorNode.left = ValueNode().apply {
                value = Data("1", if (leftDataType != null) leftDataType else rightDataType)
            }
            operatorNode.right = ValueNode().apply {
                value = Data("1", if (rightDataType != null) rightDataType else leftDataType)
            }

            outputConnectorsList[0].dataType = operatorNode.evaluate().type

            operatorNode.left = tempLeft
            operatorNode.right = tempRight
        }
        else{
            outputConnectorsList[0].dataType = DataType.UNSPECIFIED
        }
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = null
            leftDataType = null
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.right = null
            rightDataType = null
        }
        solveOutputType()
    }

    override fun getNodeOutput(): MathNode {
        return operatorNode
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {

        if(connectorOutput.dataType == DataType.UNSPECIFIED) return true

        var ans = true

        val tempRight = operatorNode.right
        val tempLeft = operatorNode.left

        if (inputConnectorsList.indexOf(connectorInput) == 0) { // индексы могут не совападать
            operatorNode.left = ValueNode().apply {
                value = Data("1", connectorOutput.dataType)
            }
            operatorNode.right = ValueNode().apply {
                value = Data("1", if (rightDataType != null) rightDataType else connectorOutput.dataType)
            }
        }
        if (inputConnectorsList.indexOf(connectorInput) == 1) {
            operatorNode.left = ValueNode().apply {
                value = Data("1", if (leftDataType != null) leftDataType else connectorOutput.dataType)
            }
            operatorNode.right = ValueNode().apply {
                value = Data("1", connectorOutput.dataType)
            }
        }




        try {
            operatorNode.evaluate()
        } catch (e: Throwable) {
            ans = false
        }

        operatorNode.right = tempRight
        operatorNode.left = tempLeft

        return ans
    }
}

