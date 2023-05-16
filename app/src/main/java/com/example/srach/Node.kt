package com.example.srach

import android.util.Log

class Node {
    var nodeInputs = mutableListOf<NodeInput>()

    val nodeOutput = NodeOutput(this)
    val name = "Node"

    init {
        nodeInputs.add(NodeInput(this))
        nodeInputs.add(NodeInput(this))
    }
}

