package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure

class Connection private constructor(private val field: Field):Drawable {
    constructor(field: Field, pos1:Vector2f, pos2: Vector2f) : this(field){
        this.pos1 = pos1
        this.pos2 = pos2
    }
    constructor(field: Field, connectorOutput: NodeViewConnector, pos2: Vector2f) : this(field){
        this.connectorOutput = connectorOutput
        this.pos2 = pos2
    }
    constructor(field: Field, pos1:Vector2f, connectorInput: NodeViewConnector) : this(field){
        this.pos1 = pos1
        this.connectorInput = connectorInput
    }
    constructor(field: Field, connectorOutput: NodeViewConnector, connectorInput: NodeViewConnector) : this(field){
        this.connectorInput = connectorInput
        this.connectorOutput = connectorOutput
    }

    private var connectorInput:NodeViewConnector? = null
    private var connectorOutput:NodeViewConnector? = null

    private var pos1 = Vector2f()
    private var pos2 = Vector2f()

    private var displayPos1 = Vector2f()
    private var displayPos2 = Vector2f()

    private var controllerLength = 250f
    private var displayControllerLength = 0f

    private val strokeWidth = 6.5f

    private var path = Path()
    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
    }
    private val pathMeasure = PathMeasure()

    private fun solveDisplayPosition(){
        val viewPos = field.viewPosition
        val viewSize = field.viewSize
        val scale = field.scale

        if(connectorInput != null){
            pos1 = connectorInput!!.globalPosition
        }
        if(connectorOutput != null){
            pos2 = connectorOutput!!.globalPosition
        }
        displayPos1.x = pos1.x * scale - viewPos.x + viewSize.x/2
        displayPos1.y = pos1.y * scale - viewPos.y + viewSize.y/2

        displayPos2.x = pos2.x * scale - viewPos.x + viewSize.x/2
        displayPos2.y = pos2.y * scale - viewPos.y + viewSize.y/2

        displayControllerLength = controllerLength * field.scale
        paint.strokeWidth = strokeWidth * field.scale
    }

    override fun draw(canvas: Canvas) {
        solveDisplayPosition()
        path.moveTo(displayPos1.x, displayPos1.y)
        path.cubicTo(displayPos1.x + displayControllerLength, displayPos1.y, displayPos2.x - displayControllerLength, displayPos2.y, displayPos2.x, displayPos2.y)
        canvas.drawPath(path, paint)
        path = Path()
    }
}