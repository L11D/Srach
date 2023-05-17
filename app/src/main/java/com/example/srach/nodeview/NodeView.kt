package com.example.srach.nodeview

import android.graphics.Canvas
import android.graphics.Color
import com.example.srach.fieldview.Drawable
import com.example.srach.fieldview.Field
//import com.example.srach.fieldview.Node
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretator.AddNode
import com.example.srach.interpretator.Node
import com.example.srach.interpretator.OperatorNode

open class NodeView(val field: Field, var position: Vector2f) : Drawable {

    var colorN = Color.BLUE

    protected var size = Vector2f(150f, 100f)
    var displayPosition = Vector2f()
        get() = field
    private var displaySize = Vector2f()

    protected val connectorRadius = 50f
    protected val connectorOffset = 25f
    protected val topPadding = 10f
    protected val bottomPadding = 10f

    val body = NodeViewBody(this).apply {
        position = Vector2f(0f, 0f)
        this@apply.size = this@NodeView.size
        round = 12f
        paint.color = colorN
    }

    var connectorsList = mutableListOf<NodeViewConnector>()

    protected var inputCount = 0
    protected var outputCount = 0

    val description = NodeViewText(this).apply {
        position = Vector2f(5f, 0f)
        textSize = 20f
        text = "node"
        paint.color = Color.WHITE
    }

    protected fun createConnectors(node: Node) {
        var height = topPadding + bottomPadding
        for (i in 0 until inputCount) {
            height += (connectorRadius + connectorOffset) * i
            connectorsList.add(
                NodeViewConnector(this, NodeInput(node)).apply {
                    position = Vector2f(
                        -connectorRadius / 2,
                        topPadding + (connectorRadius + connectorOffset) * i
                    )
                    size = Vector2f(connectorRadius, connectorRadius)
                    paint.color = Color.RED
                })
        }
        height += connectorRadius

        for (i in 0 until outputCount) {
            connectorsList.add(NodeViewConnector(this, NodeOutput(node)).apply {
                position = Vector2f(this@NodeView.size.x - connectorRadius / 2, topPadding)
                size = Vector2f(connectorRadius, connectorRadius)
                paint.color = Color.RED
            })
        }

        size.y = height
    }

    fun collision(pos: Vector2f): Boolean {
        return pos.x in displayPosition.x..(displayPosition.x + displaySize.x) &&
                pos.y in displayPosition.y..(displayPosition.y + displaySize.y)
    }

    fun connectorCollision(pos: Vector2f): NodeViewConnector? {
        for (con in connectorsList) {
            if (con.collision(pos)) return con
        }
        return null
    }

    private fun solveDisplayPosition() {
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        val scale = field.scale

        displayPosition.x = position.x * scale - viewPos.x + viewSize.x / 2
        displayPosition.y = position.y * scale - viewPos.y + viewSize.y / 2
        displaySize = size * scale
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        val right = displayPosition.x + displaySize.x
        val bottom = displayPosition.y + displaySize.y

        if (displayPosition.x < field.viewSize.x && right >= 0 &&
            displayPosition.y < field.viewSize.y && bottom >= 0
        ) {
            body.draw(canvas)
            for (c in connectorsList) {
                c.draw(canvas)
            }
            description.draw(canvas)
        }
    }
}

//    init {
//        var height = topPadding + bottomPadding
//        for (i in 0 until node.nodeInputs.size){
//            if(i == node.nodeInputs.size - 1){
//                height += (connectorRadius + connectorOffset) * i
//            }
//            connectorsList.add(
//                NodeViewConnector(this, node.nodeInputs[i]).apply {
//                    position = Vector2f(-connectorRadius/2, topPadding + (connectorRadius + connectorOffset) * i)
//                    size = Vector2f(connectorRadius, connectorRadius)
//                    paint.color = Color.RED
//                })
//        }
//        height += connectorRadius
//
//        connectorsList.add(NodeViewConnector(this, node.nodeOutput).apply {
//            position = Vector2f(this@NodeView.size.x - connectorRadius/2, topPadding)
//            size = Vector2f(connectorRadius, connectorRadius)
//            paint.color = Color.RED
//        })
//
//        size.y = height
//    }