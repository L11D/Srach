package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.LogicNode
import com.example.srach.interpretator.LogicNodeWork
import com.example.srach.interpretator.PrintNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InputableExecNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.DataTypes
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class PrintNodeView(context: Context, val printNode: PrintNode, field: Field, position: Vector2f) :
    InputableExecNodeView(context, field, position, 1, DataTypes.UNSPECIFIED) {

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        printNode.setEvaluateResult(connectorOutput.getNodeOutput())
    }

    override fun unconnect(connectorInput: NodeViewConnectorInput) {
        printNode.setEvaluateResult(null)
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        printNode.next = connectorInput.getNode()
    }

    override fun unconnectExec() {
        printNode.next = null
    }

    override fun getExecNode(): LogicNode {
        return printNode
    }
}