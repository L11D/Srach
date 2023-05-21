package com.example.srach.nodeview

import com.example.srach.interpretator.LogicNode
import com.example.srach.interpretator.LogicNodeWork
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

interface AbleToExec {
    fun connectExec(connectorInput: NodeViewConnectorInputExec<*>, connectorOutput: NodeViewConnectorOutputExec<*>)

    fun unconnectExec()
    fun getExecNode(): LogicNode
}