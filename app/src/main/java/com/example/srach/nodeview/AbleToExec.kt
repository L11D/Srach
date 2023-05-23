package com.example.srach.nodeview

import com.example.srach.interpretor.LogicNode
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

interface AbleToExec {
    fun connectExec(connectorInput: NodeViewConnectorInputExec<*>, connectorOutput: NodeViewConnectorOutputExec<*>)

    fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>)
    fun getExecNode(): LogicNode
}