package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleExecNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.DataTypes
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData


class TestNodeView(context: Context, field: Field, position: Vector2f) : InOutAbleExecNodeView(context, field, position, 2, 1, DataTypes.INT) {
    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        TODO("Not yet implemented")
    }


    override fun getNodeOutput(): MathNodeInt {
        TODO("Not yet implemented")
    }

    override fun unconnect(connectorInput: NodeViewConnectorInput) {
        TODO("Not yet implemented")
    }
}