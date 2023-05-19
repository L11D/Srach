package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Drawable
import com.example.srach.fieldview.Field
//import com.example.srach.fieldview.Node
import com.example.srach.fieldview.Vector2f
import com.example.srach.nodeview.nodeviewunits.NodeViewBody
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewText

abstract class NodeView(context: Context, val field: Field, var position: Vector2f) : Drawable {

    var colorN = Color.BLUE

    protected var size = Vector2f(150f, 100f)
    var displayPosition = Vector2f()
        get() = field
    private var displaySize = Vector2f()

    protected val connectorRadius = 50f
    protected val connectorOffset = 25f
    protected var topPadding = 10f
    protected val bottomPadding = 10f

    val body = NodeViewBody(context, this).apply {
        position = Vector2f(0f, 0f)
        this@apply.size = this@NodeView.size
        round = 12f
        paint.color = colorN
    }


    val description = NodeViewText(context, this).apply {
        position = Vector2f(5f, 0f)
        textSize = 20f
        text = "node"
        paint.color = Color.WHITE
    }

    open fun collision(pos: Vector2f): Boolean {
        return pos.x in displayPosition.x..(displayPosition.x + displaySize.x) &&
                pos.y in displayPosition.y..(displayPosition.y + displaySize.y)
    }

    private fun solveDisplayPosition() {
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        val scale = field.scale

        displayPosition.x = position.x * scale - viewPos.x + viewSize.x / 2
        displayPosition.y = position.y * scale - viewPos.y + viewSize.y / 2
        displaySize = size * scale
    }

    abstract fun drawConnectors(canvas: Canvas)
    abstract fun connectorCollision(pos: Vector2f): NodeViewConnector?

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        val right = displayPosition.x + displaySize.x
        val bottom = displayPosition.y + displaySize.y

        if (displayPosition.x < field.viewSize.x && right >= 0 &&
            displayPosition.y < field.viewSize.y && bottom >= 0
        ) {
            body.draw(canvas)
            drawConnectors(canvas)
            description.draw(canvas)
        }
    }
}

