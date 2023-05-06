package com.example.srach

class Vector2i(var x:Int, var y:Int) {
    operator fun plus(a:Vector2i) : Vector2i{
        return Vector2i(x + a.x, y + a.y)
    }
    operator fun minus(a:Vector2i) : Vector2i{
        return Vector2i(x - a.x, y - a.y)
    }
}