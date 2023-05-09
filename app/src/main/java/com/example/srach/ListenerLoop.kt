package com.example.srach

import android.graphics.Color
import android.util.Log
import kotlin.random.Random

class ListenerLoop(private val gListener: GListener, val fieldView: FieldView) : Thread() {

    private val moveViewThreshold = 50f

    override fun run() {
        super.run()
        val pos = gListener.lastTouchPos
        val size = fieldView.field.viewSize
        val running = gListener.touched

        while (running && size.x - pos.x < moveViewThreshold){
            fieldView.field.viewPosition += Vector2f(10f, 0f)
            fieldView.invalidate()
            sleep(1000)
        }
    }
}