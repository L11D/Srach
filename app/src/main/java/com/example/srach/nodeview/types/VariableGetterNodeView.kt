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
import com.example.srach.interpretor.Storage
import com.example.srach.interpretor.math.MathNode
import com.example.srach.interpretor.variables.VariableNode
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.OutputableNodeView

class VariableGetterNodeView(
    context: Context,
    field: Field,
    storage: Storage,
    position: Vector2f
) :
    OutputableNodeView(context, field, position, 1, DataType.UNSPECIFIED), AbleToUserInput {

    val variableNode = VariableNode(storage).apply { name = "" }

    init {
        description.text = "get"
    }
    override fun getNodeOutput(): MathNode {
        return variableNode
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
                    variableNameTexView.setTextColor(Color.GREEN)
                } catch (e:Throwable){
                    variableNameTexView.setTextColor(Color.RED)
                }

            }
        })

        variableNameTexView.text = SpannableStringBuilder(variableNode.name)
    }
}