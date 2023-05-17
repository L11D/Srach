package com.example.srach.fieldview

import com.example.srach.nodeview.NodeInput
import com.example.srach.nodeview.NodeOutput

class Node {
    var nodeInputs = mutableListOf<NodeInput>()

    val nodeOutput = NodeOutput(this)
    val name = "Node"

    init {
        nodeInputs.add(NodeInput(this))
        nodeInputs.add(NodeInput(this))
    }
}

