package com.example.srach

class Node {
    val nodeInputs = mutableListOf<NodeInput>()
    val nodeOutput = NodeOutput(this)
    val name = "Node"
    val OPSCharacter = "+"

    init {
        nodeInputs.add(NodeInput(this))
        nodeInputs.add(NodeInput(this))
    }
}

