package com.example.srach.nodeview

import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput

interface AbleToInput {
    fun connect(connectorInput: NodeViewConnectorInput, connectorOutput: NodeViewConnectorOutput)
    fun unconnect(connectorInput: NodeViewConnectorInput)
}