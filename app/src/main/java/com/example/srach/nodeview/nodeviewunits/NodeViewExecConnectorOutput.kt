package com.example.srach.nodeview.nodeviewunits

import android.content.Context
import com.example.srach.nodeview.AbleToExec
import com.example.srach.nodeview.NodeView

class NodeViewExecConnectorOutput<T>(context: Context, nodeView: T) : NodeViewExecConnector<T>(context, nodeView) where T:NodeView, T: AbleToExec {

}