package com.example.srach.fieldview

import android.content.Context
import android.graphics.Color
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput

class GListener(private val context: Context, private val fieldView: FieldView) :
    GestureDetector.OnGestureListener {
    var movableNodeView: NodeView? = null
        get() = field

    private var connectorInput: NodeViewConnectorInput? = null
    private var connectorOutput: NodeViewConnectorOutput? = null

    var oneTOtwo = false
    var connection: Connection? = null
    var lastComplited = false

    private val listenerLoop = ListenerLoop(this, fieldView)
    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    private fun vibrateNodeMove() {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                30,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    }

    private fun vibrateConnectionMove() {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                10,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    }

    init {
        listenerLoop.start()
    }

    var lastTouchPos = Vector2f()
        get() = field

    fun baseActions(e: MotionEvent) {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchPos = Vector2f(e.x, e.y)

                val con = fieldView.connectorsCollision(Vector2f(e.x, e.y))

                if (con is NodeViewConnectorOutput)
                    connectorOutput = con as NodeViewConnectorOutput
                else if (con is NodeViewConnectorInput)
                    connectorInput = con as NodeViewConnectorInput
                else if (con == null) {
                } else Log.d("dddd", "con isnt NodeViewConnectorOutput and NodeViewConnectorInput")

                if (con != null) {
                    vibrateConnectionMove()
                    if (connectorOutput != null) {
                        oneTOtwo = true
                        connection = Connection(
                            fieldView.field,
                            connectorOutput!!,
                            connectorOutput!!.globalPosition
                        )
                        fieldView.field.connectionsList.add(connection!!)
                    }
                    if (connectorInput != null) {
                        oneTOtwo = false
                        connection = Connection(
                            fieldView.field,
                            connectorInput!!.globalPosition,
                            connectorInput!!
                        )
                        fieldView.field.connectionsList.add(connection!!)
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {

                if (movableNodeView != null) {
                    movableNodeView!!.position += (Vector2f(
                        e.x,
                        e.y
                    ) - lastTouchPos) * (1 / fieldView.field.scale)
                } else {
                    if (connection != null) {
                        connection!!.moveEnd((Vector2f(
                            e.x,
                            e.y
                        ) - lastTouchPos) * (1 / fieldView.field.scale))


                        val con = fieldView.connectorsCollision(Vector2f(e.x, e.y))
                        if(connection!!.addConnector(con)) vibrateConnectionMove()
                    }
                }

                lastTouchPos = Vector2f(e.x, e.y)
            }

            MotionEvent.ACTION_UP -> {
                if (connection != null && !connection!!.isComplete()) {
                    connection!!.delete()
                }
                if (connection != null) connection!!.connect()

                connectorInput = null
                connectorOutput = null
                movableNodeView = null
                connection = null
                lastComplited = false
            }
        }
    }

    override fun onDown(e: MotionEvent): Boolean {

        return true
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (movableNodeView == null && connection == null) {
            fieldView.field.viewPosition += Vector2f(distanceX, distanceY)
        }
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        movableNodeView = fieldView.nodeViewsCollision(Vector2f(e.x, e.y))
        if (movableNodeView != null) {
            movableNodeView!!.colorN = Color.GREEN
            if (connection != null) {
                fieldView.field.connectionsList.removeLast()
                connection = null
                connectorInput = null
                connectorOutput = null
            }
            vibrateNodeMove()
        }

        for (con in fieldView.field.connectionsList) {
            if (con.collision(Vector2f(e.x, e.y))) {
                vibrateNodeMove()
                con.delete()
                break
            }
        }

        fieldView.invalidate()
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }
}