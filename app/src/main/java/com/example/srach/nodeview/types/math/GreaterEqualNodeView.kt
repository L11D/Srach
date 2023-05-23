package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.GreaterEqualNode
import com.example.srach.nodeview.types.OperatorNodeView

class GreaterEqualNodeView (context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, GreaterEqualNode(), field, position) {
    init {
        description.text = "GreaterEqual"
        outputConnectorsList[0].dataType = DataType.BOOL
    }
}