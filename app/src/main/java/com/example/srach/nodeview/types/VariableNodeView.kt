package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.MathNode
import com.example.srach.interpretor.VariableNode
import com.example.srach.nodeview.OutputableNodeView

class VariableNodeView(context: Context, private val variableNode: VariableNode, field: Field, position: Vector2f) :
    OutputableNodeView(context, field, position, 1, variableNode.type){
    override fun getNodeOutput(): MathNode {
        return variableNode
    }

}