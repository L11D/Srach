package com.example.srach.nodeview.types

import android.graphics.Color
import com.example.srach.nodeview.NodeView
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.Node
import com.example.srach.interpretator.OperatorNode
import com.example.srach.nodeview.NodeViewConnector

class OperatorNodeView(val operatorNode: OperatorNode, field: Field, position: Vector2f) : NodeView(field, position) {
    init {
        inputCount = 2
        outputCount = 1
        createConnectors(operatorNode)
    }
}