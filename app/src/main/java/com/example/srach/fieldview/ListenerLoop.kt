package com.example.srach.fieldview

class ListenerLoop(private val gListener: GListener, val fieldView: FieldView) : Thread() {

    private val moveViewThreshold = 75f
    private val moveSpeed = 20f
    private val moveFastSpeed = 100f
    private val stepsToSpeedUp = 25

    private fun move(delta: Vector2f){
        fieldView.field.viewPosition += delta
        if( gListener.movableNodeView != null){
            gListener.movableNodeView!!.position += delta * (1/fieldView.field.scale)
        }
        if(gListener.connection != null){
            gListener.connection!!.moveEnd(delta * (1/fieldView.field.scale))
        }
    }
    override fun run() {
        super.run()
        var speed = moveSpeed
        var i = 0
        while (true){
        if (gListener.movableNodeView != null || gListener.connection != null)
            {
                if(fieldView.field.viewSize.x - gListener.lastTouchPos.x < moveViewThreshold){
                    move(Vector2f(speed, 0f))
                    fieldView.invalidate()
                }
                else if(gListener.lastTouchPos.x < moveViewThreshold){
                    move(Vector2f(-speed, 0f))
                    fieldView.invalidate()
                }
                else if(fieldView.field.viewSize.y - gListener.lastTouchPos.y < moveViewThreshold){
                    move(Vector2f(0f, speed))
                    fieldView.invalidate()
                }
                else if(gListener.lastTouchPos.y < moveViewThreshold){
                    move(Vector2f(0f, -speed))
                    fieldView.invalidate()
                }
                else{
                    i = 0
                    speed = moveSpeed
                }
                i++
                if(i >= stepsToSpeedUp){
                    speed = moveFastSpeed
                }
            }

            sleep(50)
        }
    }
}