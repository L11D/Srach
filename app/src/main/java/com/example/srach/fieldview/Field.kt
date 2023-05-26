package com.example.srach.fieldview

import android.content.Context
import android.graphics.Canvas
import android.widget.TextView
import com.example.srach.R
import com.example.srach.interpretor.VariablesAndArraysStorage
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.variables.DeclarationVariableNode
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.types.BeginNodeView
import com.example.srach.nodeview.types.BranchNodeView
import com.example.srach.nodeview.types.DeclarationVariableNodeView
import com.example.srach.nodeview.types.LoopNodeView
import com.example.srach.nodeview.types.OperatorNodeView
import com.example.srach.nodeview.types.PrintNodeView
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

    private var variables = VariablesAndArraysStorage()

    fun findPosition(pos:Vector2f):Vector2f{
        if(nodeViewsCollisionInFieldCoords(Vector2f(pos.x+10f, pos.y+10f)) == null) return pos
        return findPosition(Vector2f(pos.x+20f, pos.y))
    }
    fun addNode(nodeName: String){
        var nodePosition = findPosition(viewPosition * (1f/scale))
        when(nodeName){
            "AddNode" ->{AddNodeView(context, this, nodePosition)}
        }
    }

    init {
        BeginNodeView(context, this, Vector2f(-300f,-500f))

        AddNodeView(context, this, Vector2f(50f, 0f))
        SubtractNodeView(context, this, Vector2f(250f, 0f))
        DivideNodeView(context, this, Vector2f(450f, 0f))
        MultiplyNodeView(context, this, Vector2f(650f, 0f))
        DivideRemainderNodeView(context, this, Vector2f(850f, 0f))

        BranchNodeView(context, this, Vector2f(600f, -500f))

        DeclarationVariableNodeView(context, variables, this, Vector2f(-200f, 100f))
        DeclarationVariableNodeView(context, variables, this, Vector2f(-200f, 200f))
        DeclarationVariableNodeView(context, variables, this, Vector2f(-200f, 300f))
        DeclarationVariableNodeView(context, variables, this, Vector2f(-200f, 400f))
        DeclarationVariableNodeView(context, variables, this, Vector2f(-200f, 500f))
        DeclarationVariableNodeView(context, variables, this, Vector2f(-200f, 600f))

        LessNodeView(context, this, Vector2f(50f, 200f))
        EqualNodeView(context, this, Vector2f(250f, 200f))
        NotEqualNodeView(context, this, Vector2f(450f, 200f))
        GreaterNodeView(context, this, Vector2f(650f, 200f))
        GreaterEqualNodeView(context, this, Vector2f(850f, 200f))
        LessEqualNodeView(context, this, Vector2f(1050f, 200f))

        PrintNodeView(context, this, Vector2f(0f, -500f))
        PrintNodeView(context, this, Vector2f(300f, -500f))
    }

    fun bindConsole(console:TextView){
        this.console = console
        for (node in nodeViewList){
            if(node is PrintNodeView) node.bindConsole(console)
        }
    }

    fun nodeViewsCollision(pos: Vector2f): NodeView?{
        for(i in nodeViewList.size - 1 downTo 0){
            if (nodeViewList[i].collision(pos)) return nodeViewList[i]
        }
        return null
    }

    fun nodeViewsCollisionInFieldCoords(pos: Vector2f): NodeView?{
        for(i in nodeViewList.size - 1 downTo 0){
            if (nodeViewList[i].collisionInFieldCoords(pos)) return nodeViewList[i]
        }
        return null
    }

    private fun createVariables(){
        for (node in nodeViewList){
            //if (node is DeclarationVariableNode) node
        }
    }

    fun goLogic(node: LogicNode) {
        try {
            if (node.next != null) {
                node.next.work()
                goLogic(node.next)
            } else println("End")
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