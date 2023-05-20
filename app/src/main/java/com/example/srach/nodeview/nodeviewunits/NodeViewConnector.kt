package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.fieldview.Drawable
import com.example.srach.fieldview.Vector2f
import com.example.srach.nodeview.NodeView

abstract class NodeViewConnector (context: Context, nodeView: NodeView) : Drawable, NodeViewUnit(context, nodeView) {

    var dataType = DataTypes.UNSPECIFIED
    var globalPosition = Vector2f()
        get() = field

    fun isSameType(con:NodeViewConnector):Boolean{
        return dataType == con.dataType
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
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawOval(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, paint)
    }
}