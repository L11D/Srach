package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.fieldview.Connection
import com.example.srach.fieldview.Drawable
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.nodeview.NodeView

abstract class NodeViewConnector (context: Context, nodeView: NodeView) : Drawable, NodeViewUnit(context, nodeView) {

    var dataType = DataType.UNSPECIFIED
    var connection:Connection? = null

    var globalPosition = Vector2f()
        get() = field

    fun isSameType(con:NodeViewConnector):Boolean{
        return dataType == con.dataType || con.dataType == DataType.UNSPECIFIED || dataType == DataType.UNSPECIFIED
    }

    fun collision(pos: Vector2f):Boolean{
        return pos.x in displayPosition.x..(displayPosition.x+displaySize.x)&&
                pos.y in displayPosition.y..(displayPosition.y+displaySize.y)
    }

    override fun solveDisplayPosition() {
        super.solveDisplayPosition()
        globalPosition = position + nodeView.position
        globalPosition.x += size.x/2
        globalPosition.y += size.y/2

        when(dataType){
            DataType.INT -> paint.color = context.getColor(R.color.intColor)
            DataType.CHAR -> paint.color = context.getColor(R.color.charColor)
            DataType.DOUBLE -> paint.color = context.getColor(R.color.doubleColor)
            DataType.BOOL -> paint.color = context.getColor(R.color.boolColor)
            DataType.STRING -> paint.color = context.getColor(R.color.stringColor)
            DataType.EXEC -> paint.color = context.getColor(R.color.execColor)
            DataType.UNSPECIFIED -> paint.color = context.getColor(R.color.unspecifiedColor)
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawOval(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, paint)
    }
}