package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.LogicNode
import com.example.srach.interpretor.MathNode
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

abstract class LoopNodeView(context: Context, field: Field, position: Vector2f, inputCount: Int, outputCount: Int) :
    InOutAbleNodeView(context, field, position, inputCount, outputCount, DataType.BOOL), AbleToExec {

    override fun initConnectors() {

        val con1 = NodeViewConnectorInputExec(context, this).apply {
            position = Vector2f(-connectorRadius / 2, topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }

        val con2 = NodeViewConnectorOutputExec(context, this).apply {
            position = Vector2f(this@LoopNodeView.size.x - connectorRadius / 2,topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        val con12 = NodeViewConnectorOutputExec(context, this).apply {
            position = Vector2f(this@LoopNodeView.size.x - connectorRadius / 2,topPadding + connectorOffset + connectorRadius)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        topPadding += connectorRadius + connectorOffset;
        super.initConnectors()
        inputConnectorsList.add(con1)
        outputConnectorsList.add(con2)
        outputConnectorsList.add(con12)
    }

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        TODO("Not yet implemented")
    }

    override fun getNodeOutput(): MathNode {
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
        TODO("Not yet implemented")
    }
}