package com.example.srach.nodeview

import android.content.Context
import android.graphics.Color
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.MathNode
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

abstract class OutputableNodeView(context:Context, field: Field, position: Vector2f, val outputCount:Int, val dataType: DataType) : NodeView(context, field, position),
    AbleToOutput {


    init {
       initConnectors()
    }

    open fun initConnectors(){
        //var height = topPadding + bottomPadding
//        for (i in 0 until inputCount) {
//            height += (connectorRadius + connectorOffset) * i
//            inputConnectorsList.add(
//                NodeViewConnectorInput(this).apply {
//                    this@ConnectableNodeView.position = Vector2f(
//                        -connectorRadius / 2,
//                        topPadding + (connectorRadius + connectorOffset) * i
//                    )
//                    size = Vector2f(connectorRadius, connectorRadius)
//                    paint.color = Color.RED
//                })
//        }
//        height += connectorRadius

        for (i in 0 until outputCount) {
            outputConnectorsList.add(NodeViewConnectorOutputData(context, this, dataType).apply {
                this@apply.position =
                    Vector2f(this@OutputableNodeView.size.x - connectorRadius / 2, topPadding)
                size = Vector2f(connectorRadius, connectorRadius)
                paint.color = Color.RED
            })
        }

        //size.y = height
    }

    abstract override fun getNodeOutput(): MathNode
}

