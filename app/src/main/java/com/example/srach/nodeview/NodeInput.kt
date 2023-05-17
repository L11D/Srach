package com.example.srach.nodeview

import com.example.srach.interpretator.Node

class NodeInput(var node: Node) {

    var nodeOutput: NodeOutput? = null
    fun setOutput(output: NodeOutput){
        nodeOutput = output
    }
}