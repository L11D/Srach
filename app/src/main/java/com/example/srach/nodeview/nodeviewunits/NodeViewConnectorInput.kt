package com.example.srach.nodeview.nodeviewunits

import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.InputableNodeView
import com.example.srach.nodeview.NodeView

class NodeViewConnectorInput private constructor(nodeView: NodeView) : NodeViewConnector(nodeView){
    lateinit var inputableNode: AbleToInput
    constructor(inputableNodeView: InputableNodeView, NULL:Int) : this(inputableNodeView){
        inputableNode = inputableNodeView
    }
    constructor(inOutAbleNodeView: InOutAbleNodeView, NULL: Int) : this(inOutAbleNodeView){
        inputableNode = inOutAbleNodeView
    }
}