package com.example.srach.nodeview.types

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import com.example.srach.R
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.ValueNode
import com.example.srach.interpretor.Storage
import com.example.srach.interpretor.logic.LogicNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.interpretor.operators.AssignmentNode
import com.example.srach.interpretor.variables.VariableNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.InOutAbleExecNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputExec
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputExec

class AssingmentNodeView(context: Context, field: Field, storage: Storage, position: Vector2f) :
    InOutAbleExecNodeView(context, field, position, 1, 1, DataType.UNSPECIFIED), AbleToUserInput {

    private val assingmentNode = AssignmentNode()
    private val variableNode = VariableNode(storage).apply { name = "" }

    init {
        assingmentNode.setVariable(variableNode)
        description.text = "set"
    }


    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {
        assingmentNode.setEvaluateResult(connectorOutput.getNodeOutput())
    }

    override fun getNodeOutput(): MathNode {
        return variableNode
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        return inputConnectorsList[0].dataType == connectorOutput.dataType
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        assingmentNode.setEvaluateResult(null)
    }

    override fun connectExec(
        connectorInput: NodeViewConnectorInputExec<*>,
        connectorOutput: NodeViewConnectorOutputExec<*>
    ) {
        assingmentNode.next = connectorInput.getNode()
    }

    override fun disconnectExec(connectorOutput: NodeViewConnectorOutputExec<*>) {
        assingmentNode.next = null
    }

    override fun getExecNode(): LogicNode {
        return assingmentNode
    }

    override fun createUserInput(layout: ViewGroup) {
        val inflater = LayoutInflater.from(context)
        val nodeLayout = inflater.inflate(R.layout.getter_variable_layout, layout)

        val variableNameTexView = nodeLayout.findViewById<EditText>(R.id.variableName)
        variableNameTexView.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                variableNode.name = s.toString()
                description.text = s.toString()

                try{
                    outputConnectorsList[0].dataType = variableNode.evaluate().type
                    inputConnectorsList[0].dataType = variableNode.evaluate().type
                    variableNameTexView.setTextColor(Color.GREEN)
                } catch (e:Throwable){
                    variableNameTexView.setTextColor(Color.RED)
                }

            }
        })

        variableNameTexView.text = SpannableStringBuilder(variableNode.name)
    }
}