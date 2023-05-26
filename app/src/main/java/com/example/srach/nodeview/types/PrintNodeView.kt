package com.example.srach.nodeview.types

import android.content.Context
import android.widget.TextView
import com.example.srach.NewConsole
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.input_and_output.PrintNode
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InputableExecNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class PrintNodeView(context: Context, field: Field, position: Vector2f) :
    InputableExecNodeView(context, field, position, 1, DataType.UNSPECIFIED) {
    private val printNode = PrintNode()
    init {
        description.text = "Print"
    }

    fun bindConsole(console:NewConsole){
        printNode.setConsole(console)
    }

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        printNode.setEvaluateResult(connectorOutput.getNodeOutput())
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        return true
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        printNode.setEvaluateResult(null)
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        printNode.next = connectorInput.getNode()
    }

    override fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>) {
        printNode.next = null
    }

    override fun getExecNode(): LogicNode {
        return printNode
    }
}