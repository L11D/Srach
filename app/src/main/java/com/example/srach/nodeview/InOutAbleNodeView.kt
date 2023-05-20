package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.nodeview.nodeviewunits.DataTypes
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

abstract class InOutAbleNodeView(context: Context, field: Field, position: Vector2f, val inputCount:Int, val outputCount:Int, var dataType: DataTypes) : NodeView(context, field, position),
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
                NodeViewConnectorInputData(context, this, dataType).apply {
                    this@apply.position = Vector2f(
                        -connectorRadius / 2,
                        topPadding + (connectorRadius + connectorOffset) * i
                    )
                    size = Vector2f(connectorRadius, connectorRadius)
                    paint.color = Color.RED
                })
        }

        for (i in 0 until outputCount) {
            outputConnectorsList.add(NodeViewConnectorOutputData(context, this, dataType).apply {
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

    abstract override fun <I, O> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where O : NodeView, I : NodeView, I:AbleToInput, O:AbleToOutput

    abstract override fun getNodeOutput(): MathNodeInt
}
