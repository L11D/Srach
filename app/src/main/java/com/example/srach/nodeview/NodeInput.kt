package com.example.srach.nodeview

import com.example.srach.fieldview.Node

class NodeInput(var node: Node) {

    var nodeOutput: NodeOutput? = null
    fun setOutput(output: NodeOutput){
        nodeOutput = output
    }
}