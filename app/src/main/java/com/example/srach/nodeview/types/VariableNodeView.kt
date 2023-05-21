package com.example.srach.nodeview.types

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.MathNode
import com.example.srach.interpretor.VariableNode
import com.example.srach.nodeview.OutputableNodeView
import com.example.srach.nodeview.nodeviewunits.DataTypes

class VariableNodeView(context: Context, private val variableNode: VariableNode, field: Field, position: Vector2f) :
    OutputableNodeView(context, field, position, 1, DataTypes.INT){
    override fun getNodeOutput(): MathNode {
        return variableNode
    }

}