package com.example.srach.nodeview

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

abstract class InputableExecNodeView(context: Context, field: Field, position: Vector2f, inputCount: Int, dataType: DataType) :
    InputableNodeView(context, field, position, inputCount, dataType), AbleToExec {

    override fun initConnectors() {

        val con1 = NodeViewConnectorInputExec(context, this).apply {
            position = Vector2f(-connectorRadius / 2, topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        val con2 = NodeViewConnectorOutputExec(context, this).apply {
            position = Vector2f(this@InputableExecNodeView.size.x - connectorRadius / 2,topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        topPadding += connectorRadius + connectorOffset;
        super.initConnectors()
        inputConnectorsList.add(con1)
        outputConnectorsList.add(con2)
    }
}