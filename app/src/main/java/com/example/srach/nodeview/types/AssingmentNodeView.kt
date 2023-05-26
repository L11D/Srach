package com.example.srach.nodeview.types

import android.content.Context
import android.view.ViewGroup
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.ValueNode
import com.example.srach.interpretor.VariablesAndArraysStorage
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.interpretor.operators.AssignmentNode
import com.example.srach.interpretor.variables.VariableNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.InOutAbleExecNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class AssingmentNodeView(context: Context, field: Field, storage: VariablesAndArraysStorage, position: Vector2f) :
    InOutAbleExecNodeView(context, field, position, 1, 1, DataType.UNSPECIFIED), AbleToUserInput {

    private val assingmentNode = AssignmentNode()
    private val variableNode = VariableNode(storage)

    init {
        assingmentNode.setVariable(variableNode)
        //setVarName("a")
    }

    private fun setVarName(name:String){
        variableNode.name = name
        inputConnectorsList[0].dataType = variableNode.evaluate().type
        outputConnectorsList[0].dataType = variableNode.evaluate().type
    }

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        assingmentNode.setEvaluateResult(connectorOutput.getNodeOutput())
    }

    override fun getNodeOutput(): MathNode {
        return variableNode
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        return inputConnectorsList[0].dataType == connectorOutput.dataType
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        assingmentNode.setEvaluateResult(null)
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        assingmentNode.next = connectorInput.getNode()
    }

    override fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>) {
        assingmentNode.next = null
    }

    override fun getExecNode(): LogicNode {
        return assingmentNode
    }

    override fun createUserInput(layout: ViewGroup) {

    }
}