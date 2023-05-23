package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.LessNode
import com.example.srach.interpretor.NotEqualNode
import com.example.srach.nodeview.types.OperatorNodeView

class NotEqualNodeView (context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, NotEqualNode(), field, position) {
    init {
        description.text = "NotEqual"
        outputConnectorsList[0].dataType = DataType.BOOL
    }
}