package com.example.srach.nodeview

import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f

abstract class InputableNodeView(field: Field, position: Vector2f, inputCount:Int) : NodeView(field, position), AbleToInput{

    var inputConnectorsList = mutableListOf<NodeViewConnectorInput>()

    init {
        var height = topPadding + bottomPadding
        for (i in 0 until inputCount) {
            if(i > 0)  height += connectorOffset
            height += connectorRadius
            inputConnectorsList.add(
                NodeViewConnectorInput(this, 0).apply {
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

    override fun connectorCollision(pos: Vector2f): NodeViewConnector? {
        for (con in inputConnectorsList) {
            if (con.collision(pos)) return con
        }
        return null
    }

    override fun drawConnectors(canvas: Canvas){
        for (c in inputConnectorsList) {
            c.draw(canvas)
        }
    }

    abstract override fun connect(
        connectorInput: NodeViewConnectorInput,
        connectorOutput: NodeViewConnectorOutput
    )
}