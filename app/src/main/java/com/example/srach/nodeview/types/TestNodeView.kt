package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.nodeview.InOutAbleExecNodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput


class TestNodeView(context: Context, field: Field, position: Vector2f) : InOutAbleExecNodeView(context, field, position, 2, 1) {

    override fun connect(
        connectorInput: NodeViewConnectorInput,
        connectorOutput: NodeViewConnectorOutput
    ) {
        TODO("Not yet implemented")
    }

    override fun getNodeOutput(): MathNodeInt {
        TODO("Not yet implemented")
    }

    override fun unconnect(connectorInput: NodeViewConnectorInput) {
        TODO("Not yet implemented")
    }
}