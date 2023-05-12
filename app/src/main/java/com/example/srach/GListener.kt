package com.example.srach

import android.content.Context
import android.graphics.Color
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class GListener(private val context: Context, private val fieldView: FieldView) :
    GestureDetector.OnGestureListener {
    var movableNodeView: NodeView? = null
        get() = field

    var nodeViewConnector1: NodeViewConnector? = null
    var nodeViewConnector2: NodeViewConnector? = null
    var oneTOtwo = false
    var connection: Connection? = null

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

                nodeViewConnector1 = fieldView.connectorsCollision(Vector2f(e.x, e.y))

                if (nodeViewConnector1 != null) {
                    vibrateConnectionMove()
                    if (nodeViewConnector1!!.nodeOutput != null) {
                        oneTOtwo = true
                        connection = Connection(
                            fieldView.field,
                            nodeViewConnector1!!,
                            nodeViewConnector1!!.globalPosition
                        )
                        fieldView.field.connectionsList.add(connection!!)
                    } else {
                        oneTOtwo = false
                        connection = Connection(
                            fieldView.field,
                            nodeViewConnector1!!.globalPosition,
                            nodeViewConnector1!!
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
                        if (oneTOtwo) {
                            connection!!.pos2 += (Vector2f(
                                e.x,
                                e.y
                            ) - lastTouchPos) * (1 / fieldView.field.scale)
                        } else {
                            connection!!.pos1 += (Vector2f(
                                e.x,
                                e.y
                            ) - lastTouchPos) * (1 / fieldView.field.scale)
                        }


                        nodeViewConnector2 = fieldView.connectorsCollision(Vector2f(e.x, e.y))

                        if (nodeViewConnector2 != null) {

                            if (oneTOtwo) {
                                if (nodeViewConnector2!!.nodeInput != null && connection!!.connectorInput == null) {
                                    connection!!.connectorInput = nodeViewConnector2
                                    vibrateConnectionMove()
                                }
                            }
                            else {
                                if (nodeViewConnector2!!.nodeOutput != null && connection!!.connectorOutput == null) {
                                    connection!!.connectorOutput = nodeViewConnector2
                                    vibrateConnectionMove()
                                }
                            }
                        }
                        else{
                            if(oneTOtwo){
                                if(connection!!.connectorInput != null){
                                    connection!!.connectorInput = null
                                    vibrateConnectionMove()
                                }
                            }
                            else{
                                if(connection!!.connectorOutput != null){
                                    connection!!.connectorOutput = null
                                    vibrateConnectionMove()
                                }
                            }
                        }

                    }
                }

                lastTouchPos = Vector2f(e.x, e.y)
            }

            MotionEvent.ACTION_UP -> {
                if (connection != null && !connection!!.isComplete()) {
                    fieldView.field.connectionsList.removeLast()
                }

                movableNodeView = null
                connection = null
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
//        Log.d("dddd", "onScroll")
        //Log.d("dddd", fieldView.getField().getViewPosition().x.toString())
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        movableNodeView = fieldView.nodeViewsCollision(Vector2f(e.x, e.y))
        if (movableNodeView != null) {
            movableNodeView!!.colorN = Color.GREEN
            if (connection != null) {
                fieldView.field.connectionsList.removeLast()
                connection = null
            }
            vibrateNodeMove()
        }
        nodeViewConnector1 = null

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