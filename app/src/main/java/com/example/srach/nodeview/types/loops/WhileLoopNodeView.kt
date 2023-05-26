package com.example.srach.nodeview.types.loops

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.loops.BeginNode
import com.example.srach.interpretor.loops.WhileLoopNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class WhileLoopNodeView(context: Context, field: Field, position: Vector2f) : LoopNodeView(context, field, position) {

    val whileLoopNode = WhileLoopNode()
    private val loopBegin = BeginNode()

    init {
        description.text = "While"
        whileLoopNode.loopBegin = loopBegin
    }

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        whileLoopNode.condition = connectorOutput.getNodeOutput()
    }

    override fun getNodeOutput(): MathNode {
        TODO("Not yet implemented")
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        return connectorOutput.dataType == DataType.BOOL
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        whileLoopNode.condition = null
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        if (outputConnectorsList.indexOf(connectorOutput) == 0) {
            loopBegin.next = connectorInput.getNode()
        }
        if (outputConnectorsList.indexOf(connectorOutput) == 1) {
            whileLoopNode.completed = connectorInput.getNode()
        }
    }

    override fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>) {
        if (outputConnectorsList.indexOf(connectorOutput) == 0) {
            loopBegin.next = null
        }
        if (outputConnectorsList.indexOf(connectorOutput) == 1) {
            whileLoopNode.completed = null
        }
    }

    override fun getExecNode(): LogicNode {
        return whileLoopNode
    }
}