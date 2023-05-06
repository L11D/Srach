package com.example.srach

class Vector2f(var x:Float, var y:Float) {
    operator fun plus(a:Vector2f) : Vector2f{
        return Vector2f(x + a.x, y + a.y)
    }
    operator fun minus(a:Vector2f) : Vector2f{
        return Vector2f(x - a.x, y - a.y)
    }
}