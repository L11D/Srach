package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleExecNodeView
import com.example.srach.nodeview.NodeView

abstract class NodeViewExecConnector <T> (context:Context, nodeView: T) : NodeViewConnector(context, nodeView) where T:NodeView, T:AbleToExec {

    init {
        paint.color = context.getColor(R.color.execConnector)
    }

    var round = 8f
        get() = field
        set(value) {field = value}
    private var displayRound = 0f

    override fun solveDisplayPosition(){
        super.solveDisplayPosition()
        displayRound = round * nodeView.field.scale
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        canvas.drawRoundRect(displayPosition.x, displayPosition.y, displayPosition.x + displaySize.x, displayPosition.y + displaySize.y, displayRound, displayRound, paint)
    }
}