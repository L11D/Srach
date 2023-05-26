package com.example.srach.nodeview

import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

interface AbleToInput {
    fun <I, O> tryToConnect(connectorInput: NodeViewConnectorInputData<I>, connectorOutput: NodeViewConnectorOutputData<O>) : Boolean
            where O:NodeView, O:AbleToOutput, I:NodeView, I:AbleToInput
    fun <I, O> connect(connectorInput: NodeViewConnectorInputData<I>, connectorOutput: NodeViewConnectorOutputData<O>)
    where O:NodeView, O:AbleToOutput, I:NodeView, I:AbleToInput
    fun disconnect(connectorInput: NodeViewConnectorInput)
}