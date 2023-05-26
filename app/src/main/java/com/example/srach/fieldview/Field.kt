package com.example.srach.fieldview

import android.content.Context
import android.graphics.Canvas
import android.widget.TextView
import com.example.srach.R
import com.example.srach.interpretor.BranchNode
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.LessNode
import com.example.srach.interpretor.LogicNode
import com.example.srach.interpretor.SubtractNode
import com.example.srach.interpretor.VariableNode
import com.example.srach.interpretor.Variables
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.types.BeginNodeView
import com.example.srach.nodeview.types.BranchNodeView
import com.example.srach.nodeview.types.LoopNodeView
import com.example.srach.nodeview.types.OperatorNodeView
import com.example.srach.nodeview.types.PrintNodeView
import com.example.srach.nodeview.types.VariableNodeView
import com.example.srach.nodeview.types.math.AddNodeView
import com.example.srach.nodeview.types.math.DivideNodeView
import com.example.srach.nodeview.types.math.DivideRemainderNodeView
import com.example.srach.nodeview.types.math.EqualNodeView
import com.example.srach.nodeview.types.math.GreaterEqualNodeView
import com.example.srach.nodeview.types.math.GreaterNodeView
import com.example.srach.nodeview.types.math.LessEqualNodeView
import com.example.srach.nodeview.types.math.LessNodeView
import com.example.srach.nodeview.types.math.MultiplyNodeView
import com.example.srach.nodeview.types.math.NotEqualNodeView
import com.example.srach.nodeview.types.math.SubtractNodeView

class Field(private val context:Context) : Drawable {
    var viewSize = Vector2i()
    var viewPosition = Vector2f()
    var scale = 1f

    private val grid = Grid(context,this)
    val nodeViewList = mutableListOf<NodeView>()
    val connectionsList = mutableListOf<Connection>()

    private var console:TextView? = null

    var activeNodeView:NodeView? = null
        set(value) {
            if (field != null) field!!.isActive = false
            if (value != null){
                field = value
                field!!.isActive = true
                nodeViewList.remove(field!!)
                nodeViewList.add(field!!)
            }
            else field = value
        }

    private var variables = Variables()
    init {
        nodeViewList.add(BeginNodeView(context, this, Vector2f(-300f,-500f)))

        nodeViewList.add(AddNodeView(context, this, Vector2f(50f, 0f)))
        nodeViewList.add(SubtractNodeView(context, this, Vector2f(250f, 0f)))
        nodeViewList.add(DivideNodeView(context, this, Vector2f(450f, 0f)))
        nodeViewList.add(MultiplyNodeView(context, this, Vector2f(650f, 0f)))
        nodeViewList.add(DivideRemainderNodeView(context, this, Vector2f(850f, 0f)))

        nodeViewList.add(BranchNodeView(context, this, Vector2f(600f, -500f)))

        nodeViewList.add(VariableNodeView(context, variables, this, Vector2f(-200f, 100f)))
        nodeViewList.add(VariableNodeView(context, variables, this, Vector2f(-200f, 200f)))
        nodeViewList.add(VariableNodeView(context, variables, this, Vector2f(-200f, 300f)))
        nodeViewList.add(VariableNodeView(context, variables, this, Vector2f(-200f, 400f)))
        nodeViewList.add(VariableNodeView(context, variables, this, Vector2f(-200f, 500f)))
        nodeViewList.add(VariableNodeView(context, variables, this, Vector2f(-200f, 600f)))

        nodeViewList.add(LessNodeView(context, this, Vector2f(50f, 200f)))
        nodeViewList.add(EqualNodeView(context, this, Vector2f(250f, 200f)))
        nodeViewList.add(NotEqualNodeView(context, this, Vector2f(450f, 200f)))
        nodeViewList.add(GreaterNodeView(context, this, Vector2f(650f, 200f)))
        nodeViewList.add(GreaterEqualNodeView(context, this, Vector2f(850f, 200f)))
        nodeViewList.add(LessEqualNodeView(context, this, Vector2f(1050f, 200f)))

        nodeViewList.add(PrintNodeView(context, this, Vector2f(0f, -500f)))
        nodeViewList.add(PrintNodeView(context, this, Vector2f(300f, -500f)))
    }

    fun bindConsole(console:TextView){
        this.console = console
        for (node in nodeViewList){
            if(node is PrintNodeView) node.bindConsole(console)
        }
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
        var a = false
        for(node in nodeViewList){
            if(node is BeginNodeView){
                goLogic(node.getNodeToWork())
                a = true
                break
            }
        }
        if (!a) println("Begin not found")
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

    fun addNodes(name: String){
        when(name){
            "AddNode" -> nodeViewList.add(AddNodeView(context, this, Vector2f(50f, 0f)))
            //"ArrayNode" -> nodeViewList.add(ArrayNodeView(context, this, Vector2f(50f, 0f)))
            //"AssignmentNode" -> nodeViewList.add(AssignmentNodeView(context, this, Vector2f(50f, 0f)))
            "BeginNode" -> nodeViewList.add(BeginNodeView(context, this, Vector2f(50f, 0f)))
            "BranchNode" -> nodeViewList.add(BranchNodeView(context, this, Vector2f(50f, 0f)))
            //"CoolNumberNode" -> nodeViewList.add(CoolNumberNodeView(context, this, Vector2f(50f, 0f)))
            //"Data" -> nodeViewList.add(DataView(context, this, Vector2f(50f, 0f)))
            "DivideNode" -> nodeViewList.add(DivideNodeView(context, this, Vector2f(50f, 0f)))
            //"DivideRemainder"-> nodeViewList.add(DivideRemainderView(context, this, Vector2f(50f, 0f)))
            "EqualNode" -> nodeViewList.add(EqualNodeView(context, this, Vector2f(50f, 0f)))
            //"GetArrayIndexNode" -> nodeViewList.add(GetArrayIndexNodeView(context, this, Vector2f(50f, 0f)))
            "GreaterEqualNode" -> nodeViewList.add(GreaterEqualNodeView(context, this, Vector2f(50f, 0f)))
            "GreaterNode" -> nodeViewList.add(GreaterNodeView(context, this, Vector2f(50f, 0f)))
            //"InputNode" -> nodeViewList.add(InputNodeView(context, this, Vector2f(50f, 0f)))
            "LessEqualNode" -> nodeViewList.add(LessEqualNodeView(context, this, Vector2f(50f, 0f)))
            "LessNode" -> nodeViewList.add(LessNodeView(context, this, Vector2f(50f, 0f)))
            "MultiplyNode" -> nodeViewList.add(MultiplyNodeView(context, this, Vector2f(50f, 0f)))
            "NotEqualNode" -> nodeViewList.add(NotEqualNodeView(context, this, Vector2f(50f, 0f)))
            //"NumberNode" -> nodeViewList.add(NumberNodeView(context, this, Vector2f(50f, 0f)))
            "PrintNode" -> nodeViewList.add(PrintNodeView(context, this, Vector2f(50f, 0f)))
            //"SetArrayIndexNode" -> nodeViewList.add(SetArrayIndexNodeView(context, this, Vector2f(50f, 0f)))
            "SubtractNode" -> nodeViewList.add(SubtractNodeView(context, this, Vector2f(50f, 0f)))
            "VariableNode" -> nodeViewList.add(VariableNodeView(context,variables,this, Vector2f(50f, 0f)))
            //"Variables" -> nodeViewList.add(VariablesView(context, this, Vector2f(50f, 0f)))
        }
    }
}