package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec


abstract class InOutAbleExecNodeView(context: Context, field: Field, position: Vector2f, inputCount: Int, outputCount: Int, dataType: DataType) :
    InOutAbleNodeView(context, field, position, inputCount, outputCount, dataType), AbleToExec {
    override fun initConnectors() {

        val con1 = NodeViewConnectorInputExec(context, this).apply {
            position = Vector2f(-connectorRadius / 2, topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        val con2 = NodeViewConnectorOutputExec(context, this).apply {
            position = Vector2f(this@InOutAbleExecNodeView.size.x - connectorRadius / 2,topPadding)
            size = Vector2f(connectorRadius, connectorRadius)
        }
        topPadding += connectorRadius + connectorOffset;
        super.initConnectors()
        inputConnectorsList.add(con1)
        outputConnectorsList.add(con2)
    }
}