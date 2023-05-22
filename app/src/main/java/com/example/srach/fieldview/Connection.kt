package com.example.srach.fieldview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import com.example.srach.nodeview.nodeviewunits.NodeViewConnector
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec
import java.lang.Math.abs
import kotlin.math.sqrt

class Connection private constructor(private val field: Field) : Drawable {
    constructor(field: Field, pos1: Vector2f, pos2: Vector2f) : this(field) {
        this.pos1 = pos1
        this.pos2 = pos2
    }

    constructor(field: Field, connectorOutput: NodeViewConnectorOutput, pos2: Vector2f) : this(field) {
        this.connectorOutput = connectorOutput
        this.pos2 = pos2
        this.paint.color = connectorOutput.paint.color
        outputFirst = true
    }

    constructor(field: Field, pos1: Vector2f, connectorInput: NodeViewConnectorInput) : this(field) {
        this.pos1 = pos1
        this.connectorInput = connectorInput
        this.paint.color = connectorInput.paint.color
        outputFirst = false
    }

    constructor(
        field: Field,
        connectorOutput: NodeViewConnectorOutput,
        connectorInput: NodeViewConnectorInput
    ) : this(field) {
        this.connectorInput = connectorInput
        this.connectorOutput = connectorOutput
    }

    var connectorInput: NodeViewConnectorInput? = null
    var connectorOutput: NodeViewConnectorOutput? = null
    var outputFirst = false
    var lastIsComplete = false

    var pos1 = Vector2f()
    var pos2 = Vector2f()

    private var displayPos1 = Vector2f()
    private var displayPos2 = Vector2f()

    private var controllerLength = 0f
    private var displayControllerLength = 0f

    private val strokeWidth = 8f

    private var path = Path()
    private val paint = Paint().apply {
        //color = Color.RED
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
    }
    private var pathMeasure = PathMeasure()

    fun delete(){
        unconnect()
        field.connectionsList.remove(this)
    }

    fun moveEnd(delta:Vector2f){
        if (!isComplete()){
            if (connectorOutput != null){
                pos2 += delta
            }
            else{
                pos1 += delta
            }
        }
    }

    fun addConnector(con:NodeViewConnector?):Boolean{
        if (outputFirst){
            if (con is NodeViewConnectorInputExec<*> && con.isSameType(connectorOutput!!)){
                connectorInput = con
            }
            else if (con is NodeViewConnectorInputData<*> && connectorOutput is NodeViewConnectorOutputData<*>){
                if ((con as NodeViewConnectorInputData<*>).tryToConnect(connectorOutput!! as NodeViewConnectorOutputData<*>)){
                    connectorInput = con
                }
            }
            else{
                connectorInput = null
            }
        }
        else{
            if (con is NodeViewConnectorOutputExec<*> && con.isSameType(connectorInput!!)){
                connectorOutput = con
            }
            else if (con is NodeViewConnectorOutputData<*> && connectorInput is NodeViewConnectorInputData<*>){
                if ((connectorInput as NodeViewConnectorInputData<*>).tryToConnect(con as NodeViewConnectorOutputData<*>)){
                    connectorOutput = con
                }
            }
            else{
                connectorOutput = null
            }
        }
        if (lastIsComplete != isComplete()) {
            lastIsComplete = isComplete()
            return true
        }
        else{
            return false
        }
    }

    fun connect(){
        if(isComplete()){
            if(connectorInput is NodeViewConnectorInputData<*>){
                (connectorInput!! as NodeViewConnectorInputData<*>).connect(connectorOutput!! as NodeViewConnectorOutputData<*>, this)
            }
            if (connectorOutput is NodeViewConnectorOutputExec<*>){
                (connectorOutput!! as NodeViewConnectorOutputExec<*>).connect(connectorInput!! as NodeViewConnectorInputExec<*>, this)
            }
        }
    }
    fun unconnect(){
        if(connectorInput != null){
            if(connectorInput is NodeViewConnectorInputData<*>){
                (connectorInput!! as NodeViewConnectorInputData<*>).unconnect()
            }
        }
        if(connectorOutput != null){
            if (connectorOutput is NodeViewConnectorOutputExec<*>){
                (connectorOutput!! as NodeViewConnectorOutputExec<*>).unconnect()
            }
        }
    }

    fun collision(pos: Vector2f): Boolean {
        val x = pos.x
        val y = pos.y
        val distanceThreshold = 25f

        val pointCoords = floatArrayOf(0f, 0f)
        var distance = Float.MAX_VALUE
        var position = 0f
        val step = 1f

        while (position <= pathMeasure.length) {
            pathMeasure.getPosTan(position, pointCoords, null)
            val dist = distanceBetween(x, y, pointCoords[0], pointCoords[1])
            if (dist < distance) {
                distance = dist
            }
            position += step
        }

        if (distance < distanceThreshold) {
            return true
        }
        return false
    }

    private fun distanceBetween(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        val dx = x1 - x2
        val dy = y1 - y2
        return sqrt(dx * dx + dy * dy)
    }

    fun isComplete(): Boolean {
        return connectorInput != null && connectorOutput != null
    }

    private fun solveDisplayPosition() {
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        val scale = field.scale

        if (connectorOutput != null) {
            pos1 = connectorOutput!!.globalPosition
        }
        if (connectorInput != null) {
            pos2 = connectorInput!!.globalPosition
        }

        controllerLength = 50f + abs(pos1.x - pos2.x) * 0.5f + abs(pos1.y - pos2.y) * 0.1f

        displayPos1.x = pos1.x * scale - viewPos.x + viewSize.x / 2
        displayPos1.y = pos1.y * scale - viewPos.y + viewSize.y / 2

        displayPos2.x = pos2.x * scale - viewPos.x + viewSize.x / 2
        displayPos2.y = pos2.y * scale - viewPos.y + viewSize.y / 2

        displayControllerLength = controllerLength * field.scale
        paint.strokeWidth = strokeWidth * field.scale
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        path.moveTo(displayPos1.x, displayPos1.y)
        path.cubicTo(
            displayPos1.x + displayControllerLength,
            displayPos1.y,
            displayPos2.x - displayControllerLength,
            displayPos2.y,
            displayPos2.x,
            displayPos2.y
        )
        pathMeasure = PathMeasure(path, false)
        canvas.drawPath(path, paint)
        path = Path()
    }
}