package com.example.srach

class NodeInput(var node: Node) {

    var nodeOutput:NodeOutput? = null
    fun setOutput(output: NodeOutput){
        nodeOutput = output
    }
}