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
import com.example.srach.interpretor.VariablesAndArraysStorage
import com.example.srach.interpretor.math.MathNode
import com.example.srach.interpretor.variables.DeclarationVariableNode
import com.example.srach.interpretor.variables.VariableNode
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.OutputableNodeView

class DeclarationVariableNodeView(context: Context, storage: VariablesAndArraysStorage, field: Field, position: Vector2f) :
    OutputableNodeView(context, field, position, 1,  DataType.UNSPECIFIED), AbleToUserInput{

    init {
        description.text = "var"
    }

    private val declarationVariableNode = DeclarationVariableNode(storage).apply { type = DataType.INT; name=""; value="0" }
    private var variableNode = VariableNode(storage)

    fun addVariableToStorage(){
        if(declarationVariableNode.name == "")throw IllegalStateException("empty variable name")
        declarationVariableNode.work()
        variableNode.name = declarationVariableNode.name
    }

    override fun getNodeOutput(): MathNode {
        return variableNode
    }

    override fun solveDisplayPosition() {
       super.solveDisplayPosition()
        for (output in outputConnectorsList){
            output.dataType = declarationVariableNode.type
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
                declarationVariableNode.name = s.toString()
                if (s.toString() != "") description.text = s.toString()
            }
        })
        variableNameTexView.text  = SpannableStringBuilder(declarationVariableNode.name)

        val variableTypeSpinner = nodeLayout.findViewById<Spinner>(R.id.variableType)
        variableTypeSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                declarationVariableNode.type = DataType.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        for (i in 0 until DataType.values().size){
            if (declarationVariableNode.type == DataType.values()[i]){
                if (declarationVariableNode.type != DataType.UNSPECIFIED && declarationVariableNode.type != DataType.EXEC)
                    variableTypeSpinner.setSelection(i)
                break
            }
        }

        val variableValueTexView = nodeLayout.findViewById<EditText>(R.id.variableValue)
        variableValueTexView.addTextChangedListener(object:TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                declarationVariableNode.value = s.toString()
            }
        })
        variableValueTexView.text  = SpannableStringBuilder(declarationVariableNode.value)
    }
}