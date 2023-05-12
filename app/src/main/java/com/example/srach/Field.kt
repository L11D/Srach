package com.example.srach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.Log

class Field(private val context:Context) : Drawable{
    var viewSize = Vector2i()
        get() = field
        set(value) {field = value}

    var viewPosition = Vector2f()
        get() =  field
        set(value) {field = value}
    var scale = 1f
        get() = field
        set(value) {field = value}

    private val grid = Grid(context,this)
    val nodeViewList = mutableListOf<NodeView>()
        get() = field
    val connectionsList = mutableListOf<Connection>()
        get() = field

    init {
        nodeViewList.add(NodeView(Node(),this, Vector2f(0f, 0f)))
        nodeViewList.add(NodeView(Node(),this, Vector2f(100f, 300f)))
        nodeViewList.add(NodeView(Node(),this, Vector2f(-100f, 300f)))
        nodeViewList.add(NodeView(Node(),this, Vector2f(0f, 500f)))
        nodeViewList.add(NodeView(Node(),this, Vector2f(0f, 1000f)))
        nodeViewList.add(NodeView(Node(),this, Vector2f(200f, 1000f)))
        nodeViewList.add(NodeView(Node(),this, Vector2f(-200f, 1200f)))
        nodeViewList[0].colorN = Color.BLACK

        connectionsList.add(Connection(this, nodeViewList[0].connectorsList[2],  nodeViewList[1].connectorsList[0]))
    }


    override fun draw(canvas:Canvas){
        canvas.drawColor(context.getColor(R.color.fieldBG))
        grid.draw(canvas)
        for (con in connectionsList){
            con.draw(canvas)
        }
        for (node in nodeViewList) {
            node.draw(canvas)
        }
    }
}