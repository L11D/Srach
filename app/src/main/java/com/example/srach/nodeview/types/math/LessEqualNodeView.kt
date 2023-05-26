package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.operators.LessEqualNode
import com.example.srach.nodeview.types.OperatorNodeView

class LessEqualNodeView (context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, LessEqualNode(), field, position) {
    init {
        description.text = "LessEqual"
        outputConnectorsList[0].dataType = DataType.BOOL
    }
}