package com.example.srach.nodeview

interface AbleToInput {
    fun connect(connectorInput: NodeViewConnectorInput, connectorOutput:NodeViewConnectorOutput)
    fun unconnect(connectorInput: NodeViewConnectorInput)
}