package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.MathNodeInt
import com.example.srach.interpretator.VariableNode
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.OutputableNodeView

class VariableNodeView(context: Context, private val variableNode: VariableNode, field: Field, position: Vector2f) : OutputableNodeView(context, field, position, 1){
    override fun getNodeOutput(): MathNodeInt {
        return variableNode
    }

}