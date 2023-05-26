package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.loops.BeginNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.OutputableNodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class BeginNodeView(context: Context, field: Field, position: Vector2f) : OutputableNodeView(context, field, position, 0, DataType.UNSPECIFIED), AbleToExec {

    val node = BeginNode()

    init {
        description.text = "Begin"
    }

    fun getNodeToWork(): LogicNode {
        return node
    }

    override fun initConnectors() {
        val con2 = NodeViewConnectorOutputExec(context, this).apply {
            position = Vector2f(this@BeginNodeView.size.x - connectorRadius / 2,topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        topPadding += connectorRadius + connectorOffset;
        super.initConnectors()
        outputConnectorsList.add(con2)
    }

    override fun getNodeOutput(): MathNode {
        TODO("Not yet implemented")
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        node.next = connectorInput.getNode()
    }

    override fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>) {
        node.next = null
    }
    override fun getExecNode(): LogicNode {
        TODO("Not yet implemented")
    }
}