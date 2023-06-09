package com.example.srach.nodeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.R
import com.example.srach.fieldview.Drawable
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.nodeview.nodeviewunits.NodeViewBody
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewText

abstract class NodeView(val context: Context, val field: Field, var position: Vector2f) : Drawable {

    init {
        field.nodeViewList.add(this)
    }

    var inputConnectorsList = mutableListOf<NodeViewConnectorInput>()
    var outputConnectorsList = mutableListOf<NodeViewConnectorOutput>()
    var isActive = false
        set(value) {
            body.isActive = value
            field = value
        }

    var size = Vector2f(150f, 100f)
    var displayPosition = Vector2f()
    private var displaySize = Vector2f()

    protected val connectorRadius = 50f
    protected val connectorOffset = 25f
    protected var topPadding = 10f
    protected val bottomPadding = 10f

    val body = NodeViewBody(context, this).apply {
        position = Vector2f(0f, 0f)
        this@apply.size = this@NodeView.size
        round = 12f
        paint.color = context.getColor(R.color.nodeBody)
    }

    val description = NodeViewText(context, this).apply {
        position = Vector2f(30f, 3f)
        textSize = 30f
        text = "node"
        paint.color = Color.WHITE
    }

    open fun collision(pos: Vector2f): Boolean {
        return pos.x in displayPosition.x..(displayPosition.x + displaySize.x) &&
                pos.y in displayPosition.y..(displayPosition.y + displaySize.y)
    }

    open fun collisionInFieldCoords(pos: Vector2f): Boolean {
        return pos.x in position.x..(position.x + size.x) &&
                pos.y in position.y..(position.y + size.y)
    }

    fun delete(){
        for (con in inputConnectorsList) con.connection?.delete()
        for (con in outputConnectorsList) con.connection?.delete()
        field.nodeViewList.remove(this)
    }

    protected open fun solveDisplayPosition() {
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        val scale = field.scale

        displayPosition.x = position.x * scale - viewPos.x + viewSize.x / 2
        displayPosition.y = position.y * scale - viewPos.y + viewSize.y / 2
        displaySize = size * scale

        body.size = size
    }

    fun drawConnectors(canvas: Canvas){
        for (c in inputConnectorsList) {
            c.draw(canvas)
        }
        for (c in outputConnectorsList) {
            c.draw(canvas)
        }
    }
    fun connectorCollision(pos: Vector2f): NodeViewConnector?{
        for (con in inputConnectorsList) {
            if (con.collision(pos)) return con
        }
        for (con in outputConnectorsList) {
            if (con.collision(pos)) return con
        }
        return null
    }


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

