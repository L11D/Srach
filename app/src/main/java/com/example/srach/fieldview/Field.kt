package com.example.srach.fieldview

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.interpretator.AddNode
import com.example.srach.interpretator.Node
import com.example.srach.interpretator.VariableNode
import com.example.srach.interpretator.Variables
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.types.OperatorNodeView
import com.example.srach.nodeview.types.VariableNodeView

class Field(private val context:Context) : Drawable {
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

    private var variables = Variables()
    init {
        nodeViewList.add(OperatorNodeView(AddNode(),this, Vector2f(0f, 0f)))
        nodeViewList.add(VariableNodeView(VariableNode("a", variables).apply { value = 10 },this, Vector2f(300f, 0f)))
        nodeViewList.add(VariableNodeView(VariableNode("a", variables).apply { value = 5 },this, Vector2f(300f, 100f)))
//        nodeViewList.add(NodeView(AddNode(),this, Vector2f(200f, 0f)))
//        nodeViewList.add(NodeView(AddNode(),this, Vector2f(400f, 0f)))
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