package com.example.srach.nodeview.types.loops

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.loops.EndNode
import com.example.srach.interpretor.loops.LoopNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleExecNodeView
import com.example.srach.nodeview.InputableNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class EndNodeView(context: Context, field: Field, loopNode:LoopNode, position: Vector2f) :
    InputableNodeView(context, field, position, 0, DataType.UNSPECIFIED), AbleToExec {

    val endNode = EndNode()

    init {
        description.text = "Loop end"
        endNode.loopNode = loopNode
    }

    override fun initConnectors() {
        val con1 = NodeViewConnectorInputExec(context, this).apply {
            position = Vector2f(-connectorRadius / 2, topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        topPadding += connectorRadius + connectorOffset;
        super.initConnectors()
        inputConnectorsList.add(con1)
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        TODO("Not yet implemented")
    }

    override fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>) {
        TODO("Not yet implemented")
    }

    override fun getExecNode(): LogicNode {
        return endNode;
    }

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        TODO("Not yet implemented")
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        TODO("Not yet implemented")
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        TODO("Not yet implemented")
    }


}