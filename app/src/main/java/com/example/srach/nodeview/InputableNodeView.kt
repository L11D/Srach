package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

abstract class InputableNodeView(context: Context, field: Field, position: Vector2f, val inputCount:Int, val dataType: DataType) : NodeView(context, field, position), AbleToInput{

    init {
        initConnectors()
    }

    open fun initConnectors(){
        var height = topPadding + bottomPadding
        for (i in 0 until inputCount) {
            if(i > 0)  height += connectorOffset
            height += connectorRadius
            inputConnectorsList.add(
                NodeViewConnectorInputData(context, this, dataType).apply {
                    this@apply.position = Vector2f(
                        -connectorRadius / 2,
                        topPadding + (connectorRadius + connectorOffset) * i
                    )
                    size = Vector2f(connectorRadius, connectorRadius)
                    paint.color = Color.RED
                })
        }
        size.y = height
    }

    abstract override fun <I, O> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where O : NodeView, I : NodeView, I:AbleToInput, O:AbleToOutput
}