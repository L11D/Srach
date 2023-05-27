package com.example.srach.nodeview.types.arrays

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.arrays.SetArrayIndexNode
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

class SetArrayIndexNodeView(context: Context, field: Field, position: Vector2f) :
    InputableExecNodeView(context, field, position, 2, DataType.UNSPECIFIED) {

    private val setArrayIndexNode = SetArrayIndexNode()

    init {
        inputConnectorsList[0].dataType = DataType.INT
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