package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DivideNode
import com.example.srach.nodeview.types.OperatorNodeView

class DivideNodeView(context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, DivideNode(), field, position) {
    init {
        description.text = "Div"
    }
}