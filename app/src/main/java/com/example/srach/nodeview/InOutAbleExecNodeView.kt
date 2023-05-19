package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.nodeview.nodeviewunits.NodeViewExecConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewExecConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewExecConnectorOutput


abstract class InOutAbleExecNodeView(context: Context, field: Field, position: Vector2f, inputCount: Int, outputCount: Int) : InOutAbleNodeView(context, field, position,
    inputCount,
    outputCount
), AbleToExec {

    lateinit var execConnectorInput: NodeViewExecConnectorInput<InOutAbleExecNodeView>
    lateinit var execConnectorOutput: NodeViewExecConnectorOutput<InOutAbleExecNodeView>

    override fun initConnectors() {
        execConnectorInput = NodeViewExecConnectorInput(context, this)
        execConnectorInput.position = Vector2f(-connectorRadius / 2, topPadding)
        execConnectorInput.size = Vector2f(connectorRadius, connectorRadius)

        execConnectorOutput = NodeViewExecConnectorOutput(context, this)
        execConnectorOutput.position = Vector2f(this.size.x - connectorRadius / 2,topPadding)
        execConnectorOutput.size = Vector2f(connectorRadius, connectorRadius)
        
        topPadding += connectorRadius + connectorOffset;
        super.initConnectors()
    }

    override fun drawConnectors(canvas: Canvas) {
        super.drawConnectors(canvas)
        execConnectorInput.draw(canvas)
        execConnectorOutput.draw(canvas)
    }
}