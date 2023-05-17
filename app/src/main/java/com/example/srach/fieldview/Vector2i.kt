package com.example.srach.fieldview
class Vector2i() {
    var x = 0
        get() = field
        set(value) {field = value}

    var y = 0
        get() = field
        set(value) {field = value}
    constructor(x:Int, y:Int) : this() {
        this.x = x
        this.y = y
    }

    operator fun plus(a: Vector2i) : Vector2i {
        return Vector2i(x + a.x, y + a.y)
    }
    operator fun minus(a: Vector2i) : Vector2i {
        return Vector2i(x - a.x, y - a.y)
    }
}