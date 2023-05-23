package com.example.srach.nodeview.types.math

import android.content.Context
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.AddNode
import com.example.srach.nodeview.types.OperatorNodeView

class AddNodeView(context: Context, field: Field, position: Vector2f) :
    OperatorNodeView(context, AddNode(), field, position) {
        init {
            description.text = "Add"
        }
}