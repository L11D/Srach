package com.example.srach

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.Log
import java.lang.Math.abs
import kotlin.math.sqrt

class Connection private constructor(private val field: Field) : Drawable {
    constructor(field: Field, pos1: Vector2f, pos2: Vector2f) : this(field) {
        this.pos1 = pos1
        this.pos2 = pos2
    }

    constructor(field: Field, connectorOutput: NodeViewConnector, pos2: Vector2f) : this(field) {
        this.connectorOutput = connectorOutput
        this.pos2 = pos2
    }

    constructor(field: Field, pos1: Vector2f, connectorInput: NodeViewConnector) : this(field) {
        this.pos1 = pos1
        this.connectorInput = connectorInput
    }

    constructor(
        field: Field,
        connectorOutput: NodeViewConnector,
        connectorInput: NodeViewConnector
    ) : this(field) {
        this.connectorInput = connectorInput
        this.connectorOutput = connectorOutput
    }

    var connectorInput: NodeViewConnector? = null
    var connectorOutput: NodeViewConnector? = null

    var pos1 = Vector2f()
    var pos2 = Vector2f()

    private var displayPos1 = Vector2f()
    private var displayPos2 = Vector2f()

    private var controllerLength = 0f
    private var displayControllerLength = 0f

    private val strokeWidth = 8f

    private var path = Path()
    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
    }
    private var pathMeasure = PathMeasure()

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
            Log.d("dddd", "curvePress")
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