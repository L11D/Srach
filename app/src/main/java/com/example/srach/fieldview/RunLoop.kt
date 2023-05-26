package com.example.srach.fieldview

class RunLoop(val field:Field) : Thread() {
    override fun run() {
        super.run()
        field.run()
    }
}