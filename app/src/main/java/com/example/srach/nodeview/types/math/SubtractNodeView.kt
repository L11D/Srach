package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.SubtractNode
import com.example.srach.nodeview.types.OperatorNodeView

class SubtractNodeView(context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, SubtractNode(), field, position) {
    init {
        description.text = "Sub"
    }
}