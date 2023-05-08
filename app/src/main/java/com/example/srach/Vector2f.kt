package com.example.srach

class Vector2f() {
    var x = 0f
        get() = field
        set(value) {field = value}

    var y = 0f
        get() = field
        set(value) {field = value}

    constructor(x:Float, y:Float) : this() {
        this.x = x
        this.y = y
    }
    operator fun plus(a:Vector2f) : Vector2f{
        return Vector2f(x + a.x, y + a.y)
    }
    operator fun minus(a:Vector2f) : Vector2f{
        return Vector2f(x - a.x, y - a.y)
    }
}