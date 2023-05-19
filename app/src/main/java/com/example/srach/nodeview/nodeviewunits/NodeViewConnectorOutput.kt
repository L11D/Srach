package com.example.srach.nodeview.nodeviewunits

import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.OutputableNodeView

class NodeViewConnectorOutput private constructor(nodeView: NodeView) : NodeViewConnector(nodeView){
    lateinit var outputableNode: AbleToOutput
    constructor(outputableNodeView: OutputableNodeView, NULL:Int) : this(outputableNodeView){
        outputableNode = outputableNodeView
    }
    constructor(inOutAbleNodeView: InOutAbleNodeView, NULL: Int) : this(inOutAbleNodeView){
        outputableNode = inOutAbleNodeView
    }
}