package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.operators.MultiplyNode
import com.example.srach.nodeview.types.OperatorNodeView

class MultiplyNodeView (context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, MultiplyNode(), field, position) {
    init {
        description.text = "Mul"
    }
}