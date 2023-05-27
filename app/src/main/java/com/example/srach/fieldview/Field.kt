package com.example.srach.fieldview

import android.content.Context
import android.graphics.Canvas
import android.widget.TextView
import com.example.srach.NewConsole
import com.example.srach.R
import com.example.srach.interpretor.Storage
import com.example.srach.interpretor.arrays.DeclarationArrayNode
import com.example.srach.interpretor.arrays.GetArrayIndexNode

import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.loops.WhileLoopNode
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.types.AssingmentNodeView
import com.example.srach.nodeview.types.BeginNodeView
import com.example.srach.nodeview.types.BranchNodeView
import com.example.srach.nodeview.types.DeclarationVariableNodeView
import com.example.srach.nodeview.types.PrintNodeView
import com.example.srach.nodeview.types.VariableGetterNodeView
import com.example.srach.nodeview.types.arrays.DeclarationArrayNodeView
import com.example.srach.nodeview.types.arrays.GetArrayIndexValueNodeView
import com.example.srach.nodeview.types.arrays.SetArrayIndexNodeView
import com.example.srach.nodeview.types.loops.EndNodeView
import com.example.srach.nodeview.types.loops.WhileLoopNodeView
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

class Field(private val context: Context) : Drawable {
    var viewSize = Vector2i()
    var viewPosition = Vector2f()
    var scale = 1f

    private val grid = Grid(context, this)
    val nodeViewList = mutableListOf<NodeView>()
    val connectionsList = mutableListOf<Connection>()

    var console: NewConsole? = null

    var activeNodeView: NodeView? = null
        set(value) {
            if (field != null) field!!.isActive = false
            if (value != null) {
                field = value
                field!!.isActive = true
                nodeViewList.remove(field!!)
                nodeViewList.add(field!!)
            } else field = value
        }

    private var variables = Storage()

    fun findPosition(pos: Vector2f): Vector2f {
        if (nodeViewsCollisionInFieldCoords(Vector2f(pos.x + 10f, pos.y + 10f)) == null) return pos
        return findPosition(Vector2f(pos.x + 20f, pos.y))
    }

    private fun addWhileLoopNodeView(pos: Vector2f){
        val loop = WhileLoopNodeView(context, this, pos)
        EndNodeView(context, this, loop.whileLoopNode, Vector2f(pos.x + loop.size.x + 100f, pos.y))
    }

    fun addNode(name: String) {
        var nodePosition = findPosition(viewPosition * (1f / scale))
        when (name) {
            "AddNode" -> AddNodeView(context, this, nodePosition)
            "AssignmentNode" -> AssingmentNodeView(context, this,variables, nodePosition)
            "BeginNode" -> BeginNodeView(context, this, nodePosition)
            "BranchNode" -> BranchNodeView(context, this, nodePosition)
            "DivideNode" -> DivideNodeView(context, this, nodePosition)
            "DivideRemainder" -> nodeViewList.add(
                DivideRemainderNodeView(
                    context,
                    this,
                    nodePosition
                )
            )
            "VariableGetterNode" -> VariableGetterNodeView(context, this,variables, nodePosition)
            "DeclarationVariableNode" -> DeclarationVariableNodeView(context, this,variables, nodePosition)
            "WhileLoopNode" -> addWhileLoopNodeView(nodePosition)
            "DeclarationArrayNode" -> DeclarationArrayNodeView(context,this, variables ,nodePosition)
            "EqualNode" -> EqualNodeView(context, this, nodePosition)
            "GetArrayIndexValueNode" -> nodeViewList.add(GetArrayIndexValueNodeView(context, this,variables ,nodePosition))
            "GreaterEqualNode" -> GreaterEqualNodeView(context, this, nodePosition)
            "GreaterNode" -> GreaterNodeView(context, this, nodePosition)
            "LessEqualNode" -> LessEqualNodeView(context, this, nodePosition)
            "LessNode" -> LessNodeView(context, this, nodePosition)
            "MultiplyNode" -> MultiplyNodeView(context, this, nodePosition)
            "NotEqualNode" -> NotEqualNodeView(context, this, nodePosition)
            "PrintNode" -> PrintNodeView(context, this, nodePosition)
            "SetArrayIndexNode" -> SetArrayIndexNodeView(context, this, nodePosition)
            "SubtractNode" -> SubtractNodeView(context, this, nodePosition)
            "VariableNode" -> DeclarationVariableNodeView(context, this, variables, nodePosition)
        }
    }

    init {
        BeginNodeView(context, this, Vector2f(-300f, -500f))

//        AddNodeView(context, this, Vector2f(50f, 0f))
//        SubtractNodeView(context, this, Vector2f(250f, 0f))
//        DivideNodeView(context, this, Vector2f(450f, 0f))
//        MultiplyNodeView(context, this, Vector2f(650f, 0f))
//        DivideRemainderNodeView(context, this, Vector2f(850f, 0f))
//
//        BranchNodeView(context, this, Vector2f(600f, -500f))
//
//        addWhileLoopNodeView(Vector2f(900f, -500f))
//
//        DeclarationArrayNodeView(context, this, variables, Vector2f(-400f, - 300f))
//        GetArrayIndexValueNodeView(context, this, variables, Vector2f(-400f, - 300f))
//
//        AssingmentNodeView(context, this, variables, Vector2f(-200f, -100f))
//        VariableGetterNodeView(context, this, variables, Vector2f(-200f, -300f))
//        VariableGetterNodeView(context, this, variables, Vector2f(-200f, -200f))
//
//        DeclarationVariableNodeView(context, this, variables, Vector2f(-200f, 100f))
//        DeclarationVariableNodeView(context, this, variables, Vector2f(-200f, 200f))
//        DeclarationVariableNodeView(context, this, variables,  Vector2f(-200f, 300f))
//
//        LessNodeView(context, this, Vector2f(50f, 200f))
//        EqualNodeView(context, this, Vector2f(250f, 200f))
//        NotEqualNodeView(context, this, Vector2f(450f, 200f))
//        GreaterNodeView(context, this, Vector2f(650f, 200f))
//        GreaterEqualNodeView(context, this, Vector2f(850f, 200f))
//        LessEqualNodeView(context, this, Vector2f(1050f, 200f))
//
//        PrintNodeView(context, this, Vector2f(0f, -500f))
//        PrintNodeView(context, this, Vector2f(300f, -500f))
    }

    private fun bindConsole() {
        for (node in nodeViewList) {
            if (node is PrintNodeView && console != null) node.bindConsole(console!!)
        }
    }

    private fun applyStartValues(){
        for (node in nodeViewList){
            if(node is DeclarationVariableNodeView) node.applyStartValue()
            if(node is DeclarationArrayNodeView) node.applyStartValue()
        }
    }

    fun nodeViewsCollision(pos: Vector2f): NodeView? {
        for (i in nodeViewList.size - 1 downTo 0) {
            if (nodeViewList[i].collision(pos)) return nodeViewList[i]
        }
        return null
    }

    fun nodeViewsCollisionInFieldCoords(pos: Vector2f): NodeView? {
        for (i in nodeViewList.size - 1 downTo 0) {
            if (nodeViewList[i].collisionInFieldCoords(pos)) return nodeViewList[i]
        }
        return null
    }

    fun goLogic(node: LogicNode) {
        if (node.next != null) {
            node.next.work()
            goLogic(node.next)
        } else println("End")
    }

    fun run() {
        try {
            var a = false
            applyStartValues()
            bindConsole()
            for (node in nodeViewList) {
                if (node is BeginNodeView) {
                    goLogic(node.getNodeToWork())
                    a = true
                    break
                }
            }
            if (!a) {
                console!!.inputText("Begin not found")
                println("Begin not found")
            }
        } catch (e: Throwable) {
            console!!.inputText(e.message.toString())
            println(e.message)

        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(context.getColor(R.color.fieldBG))
        grid.draw(canvas)
        for (con in connectionsList) {
            con.draw(canvas)
        }
        for (node in nodeViewList) {
            node.draw(canvas)
        }
    }
}
 