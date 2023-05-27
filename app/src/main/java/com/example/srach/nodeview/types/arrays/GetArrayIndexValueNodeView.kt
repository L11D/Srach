package com.example.srach.nodeview.types.arrays

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
import com.example.srach.interpretor.Storage
import com.example.srach.interpretor.arrays.ArrayNode
import com.example.srach.interpretor.arrays.GetArrayIndexNode
import com.example.srach.interpretor.math.MathNode
import com.example.srach.nodeview.AbleToInput
import com.example.srach.nodeview.AbleToOutput
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.InOutAbleNodeView
import com.example.srach.nodeview.NodeView
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInput
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorInputData
import com.example.srach.nodeview.nodeviewunits.NodeViewConnectorOutputData

class GetArrayIndexValueNodeView(context: Context, field: Field, storage: Storage, position: Vector2f) :
    InOutAbleNodeView(context, field, position, 1, 1, DataType.UNSPECIFIED), AbleToUserInput {

    val arrayNode = ArrayNode(storage).apply { name = "" }
    val getArrayIndexNode = GetArrayIndexNode()

    init {
        getArrayIndexNode.setArray(arrayNode)
    }

    override fun <I : NodeView, O : NodeView> connect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ) where I : AbleToInput, O : AbleToOutput {

        getArrayIndexNode.setIndex(connectorOutput.getNodeOutput())
    }

    override fun getNodeOutput(): MathNode {
        return getArrayIndexNode
    }

    override fun <I : NodeView, O : NodeView> tryToConnect(
        connectorInput: NodeViewConnectorInputData<I>,
        connectorOutput: NodeViewConnectorOutputData<O>
    ): Boolean where I : AbleToInput, O : AbleToOutput {
        return connectorOutput.dataType == DataType.INT
    }

    override fun disconnect(connectorInput: NodeViewConnectorInput) {
        getArrayIndexNode.setIndex(null)
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

                arrayNode.name = s.toString()
                description.text = s.toString()
                getArrayIndexNode.setArray(arrayNode)

                try{
                    outputConnectorsList[0].dataType = getArrayIndexNode.evaluate().type
                    variableNameTexView.setTextColor(Color.GREEN)
                } catch (e:Throwable){
                    variableNameTexView.setTextColor(Color.RED)
                }
            }
        })

        variableNameTexView.text = SpannableStringBuilder(arrayNode.name)
    }
}