package com.example.srach.nodeview

import com.example.srach.fieldview.Node

class NodeOutput(val node: Node) {

    private var nodeInput: NodeInput? = null

    fun setInput(input: NodeInput){
        nodeInput = input
    }
}