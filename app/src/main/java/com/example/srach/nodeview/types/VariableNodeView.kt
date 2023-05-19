package com.example.srach.nodeview.types

import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.interpretator.VariableNode
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.OutputableNodeView

class VariableNodeView(private val variableNode: VariableNode, field: Field, position: Vector2f) : OutputableNodeView(field, position, 1){
    override fun getNodeOutput(): MathNodeInt {
        return variableNode
    }

}