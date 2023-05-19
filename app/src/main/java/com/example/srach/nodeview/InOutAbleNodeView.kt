package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewExecConnector

abstract class InOutAbleNodeView(val context: Context, field: Field, position: Vector2f, val inputCount:Int, val outputCount:Int) : NodeView(context, field, position),
    AbleToInput, AbleToOutput {

    var inputConnectorsList = mutableListOf<NodeViewConnectorInput>()
    var outputConnectorsList = mutableListOf<NodeViewConnectorOutput>()
    init {
        initConnectors()
    }

    open fun initConnectors(){
        var height = topPadding + bottomPadding
        for (i in 0 until inputCount) {
            if(i > 0)  height += connectorOffset
            height += connectorRadius
            inputConnectorsList.add(
                NodeViewConnectorInput(context, this, 0).apply {
                    this@apply.position = Vector2f(
                        -connectorRadius / 2,
                        topPadding + (connectorRadius + connectorOffset) * i
                    )
                    size = Vector2f(connectorRadius, connectorRadius)
                    paint.color = Color.RED
                })
        }

        for (i in 0 until outputCount) {
            outputConnectorsList.add(NodeViewConnectorOutput(context, this, 0).apply {
                this@apply.position =
                    Vector2f(this@InOutAbleNodeView.size.x - connectorRadius / 2, topPadding)
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
        for (con in outputConnectorsList) {
            if (con.collision(pos)) return con
        }
        return null
    }

    override fun drawConnectors(canvas: Canvas) {
        for (c in inputConnectorsList) {
            c.draw(canvas)
        }
        for (c in outputConnectorsList) {
            c.draw(canvas)
        }
    }

    abstract override fun connect(
        connectorInput: NodeViewConnectorInput,
        connectorOutput: NodeViewConnectorOutput
    )

    abstract override fun getNodeOutput(): MathNodeInt
}
