package com.example.srach.nodeview

import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt

abstract class OutputableNodeView(field: Field, position: Vector2f, outputCount:Int) : NodeView(field, position),
    AbleToOutput {

    var outputConnectorsList = mutableListOf<NodeViewConnectorOutput>()

    init {
//        var height = topPadding + bottomPadding
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
            outputConnectorsList.add(NodeViewConnectorOutput(this, 0).apply {
                this@apply.position =
                    Vector2f(this@OutputableNodeView.size.x - connectorRadius / 2, topPadding)
                size = Vector2f(connectorRadius, connectorRadius)
                paint.color = Color.RED
            })
        }

        //size.y = height
    }

    override fun connectorCollision(pos: Vector2f): NodeViewConnector? {
        for (con in outputConnectorsList) {
            if (con.collision(pos)) return con
        }
        return null
    }

    override fun drawConnectors(canvas: Canvas) {
        for (c in outputConnectorsList) {
            c.draw(canvas)
        }
    }

    abstract override fun getNodeOutput(): MathNodeInt
}

