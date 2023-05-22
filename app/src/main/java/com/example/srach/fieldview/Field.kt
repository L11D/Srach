package com.example.srach.fieldview

import android.content.Context
import android.graphics.Canvas
import com.example.srach.R
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.LogicNode
import com.example.srach.interpretor.SubtractNode
import com.example.srach.interpretor.VariableNode
import com.example.srach.interpretor.Variables
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.types.BeginNodeView
import com.example.srach.nodeview.types.OperatorNodeView
import com.example.srach.nodeview.types.PrintNodeView
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
        nodeViewList.add(BeginNodeView(context, this, Vector2f(-300f,0f)))
        nodeViewList.add(OperatorNodeView(context, SubtractNode(),this, Vector2f(0f, 0f)))
        nodeViewList.add(VariableNodeView(context, VariableNode("a", DataType.INT, variables).apply {value="10"}, this, Vector2f(-200f, 0f)))
        nodeViewList.add(VariableNodeView(context, VariableNode("b", DataType.INT, variables).apply {value="5"}, this, Vector2f(-200f, 200f)))
        nodeViewList.add(VariableNodeView(context, VariableNode("c", DataType.STRING, variables).apply {value="5"}, this, Vector2f(-200f, 300f)))
        nodeViewList.add(VariableNodeView(context, VariableNode("d", DataType.DOUBLE, variables).apply {value="5"}, this, Vector2f(-200f, 400f)))
        nodeViewList.add(VariableNodeView(context, VariableNode("r", DataType.CHAR, variables).apply {value="5"}, this, Vector2f(-200f, 500f)))
        nodeViewList.add(VariableNodeView(context, VariableNode("g", DataType.BOOL, variables).apply {value="0"}, this, Vector2f(-200f, 600f)))
        nodeViewList.add(PrintNodeView(context, this, Vector2f(0f, -500f)))
        nodeViewList.add(PrintNodeView(context, this, Vector2f(300f, -500f)))
    }

    fun goLogic(node: LogicNode) {
        try {
            if (node.next != null) {
                node.next.work()
                goLogic(node.next)
            } else print("End")
        }catch (e:Throwable){
            println(e.message)
        }
    }
    fun run(){
        if(nodeViewList[0] is BeginNodeView){
            goLogic((nodeViewList[0] as BeginNodeView).getNodeToWork())
        }
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