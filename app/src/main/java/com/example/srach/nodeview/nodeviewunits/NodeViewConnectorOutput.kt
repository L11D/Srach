package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.OutputableNodeView

//class NodeViewConnectorOutput(context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView) {
//
//}

class NodeViewConnectorOutput private constructor(context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView){
    lateinit var outputableNode: AbleToOutput
    constructor(context: Context, outputableNodeView: OutputableNodeView, NULL:Int) : this(context, outputableNodeView){
        outputableNode = outputableNodeView
    }
    constructor(context: Context, inOutAbleNodeView: InOutAbleNodeView, NULL: Int) : this(context, inOutAbleNodeView){
        outputableNode = inOutAbleNodeView
    }
}