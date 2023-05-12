package com.example.srach

class NodeOutput(val node: Node) {

    private var nodeInput:NodeInput? = null

    fun setInput(input: NodeInput){
        nodeInput = input
    }
}