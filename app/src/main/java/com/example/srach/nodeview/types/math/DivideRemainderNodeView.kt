package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DivideRemainder
import com.example.srach.nodeview.types.OperatorNodeView

class DivideRemainderNodeView (context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, DivideRemainder(), field, position) {
    init {
        description.text = "DivideRemainder"
    }
}