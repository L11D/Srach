package com.example.srach.nodeview

import com.example.srach.interpretator.Node

class NodeOutput(val node: Node) {

    private var nodeInput: NodeInput? = null

    fun setInput(input: NodeInput){
        nodeInput = input
    }
}