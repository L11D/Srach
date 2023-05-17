package com.example.srach.nodeview.types

import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.VariableNode
import com.example.srach.nodeview.NodeView

class VariableNodeView(val variableNode: VariableNode, field: Field, position: Vector2f) :
    NodeView(field, position) {
    init {
        outputCount = 1
        createConnectors(variableNode)
    }
}