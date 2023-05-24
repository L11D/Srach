package com.example.srach.nodeview.types

import android.content.Context
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import com.example.srach.R
import com.example.srach.fieldview.Field
import com.example.srach.fieldview.Vector2f
import com.example.srach.interpretor.DataType
import com.example.srach.interpretor.MathNode
import com.example.srach.interpretor.VariableNode
import com.example.srach.interpretor.Variables
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.OutputableNodeView

class VariableNodeView(context: Context, variables: Variables, field: Field, position: Vector2f) :
    OutputableNodeView(context, field, position, 1,  DataType.UNSPECIFIED), AbleToUserInput{

    private val variableNode = VariableNode("a", DataType.INT, variables)


    override fun getNodeOutput(): MathNode {
        return variableNode
    }
    override fun solveDisplayPosition() {
       super.solveDisplayPosition()
        for (output in outputConnectorsList){
            output.dataType = variableNode.type
        }
    }

    override fun createUserInput(layout: ViewGroup) {
        val inflater = LayoutInflater.from(context)
        val nodeLayout = inflater.inflate(R.layout.variable_node_layout, layout)

        val variableNameTexView = nodeLayout.findViewById<EditText>(R.id.variableName)
        variableNameTexView.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                variableNode.name = s.toString()
            }
        })
        variableNameTexView.text  = SpannableStringBuilder(variableNode.name)

        val variableTypeSpinner = nodeLayout.findViewById<Spinner>(R.id.variableType)
        variableTypeSpinner.setOnItemSelectedListener(object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                variableNode.type = DataType.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
        for (i in 0 until DataType.values().size){
            if (variableNode.type == DataType.values()[i]){
                if (variableNode.type != DataType.UNSPECIFIED && variableNode.type != DataType.EXEC)
                    variableTypeSpinner.setSelection(i)
                break
            }
        }
    }

}