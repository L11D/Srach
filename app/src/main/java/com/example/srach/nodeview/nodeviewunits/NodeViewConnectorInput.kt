package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.InputableNodeView
import com.example.srach.nodeview.NodeView

//class NodeViewConnectorInput (context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView) {
//
//}
//}
class NodeViewConnectorInput private constructor(context: Context, nodeView: NodeView) : NodeViewConnector(context, nodeView){
    lateinit var inputableNode: AbleToInput
    constructor(context: Context, inputableNodeView: InputableNodeView, NULL:Int) : this(context, inputableNodeView){
        inputableNode = inputableNodeView
    }
    constructor(context: Context, inOutAbleNodeView: InOutAbleNodeView, NULL: Int) : this(context, inOutAbleNodeView){
        inputableNode = inOutAbleNodeView
    }
}
