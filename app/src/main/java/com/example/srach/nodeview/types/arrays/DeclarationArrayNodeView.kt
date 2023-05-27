package com.example.srach.nodeview.types.arrays

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
import com.example.srach.interpretor.Storage
import com.example.srach.interpretor.arrays.ArrayNode
import com.example.srach.interpretor.arrays.DeclarationArrayNode
import com.example.srach.interpretor.arrays.SetArrayIndexNode
import com.example.srach.nodeview.AbleToUserInput
import com.example.srach.nodeview.NodeView

class DeclarationArrayNodeView(context: Context, field: Field, storage: Storage, position: Vector2f) : NodeView(context, field, position),
    AbleToUserInput {

    private val declarationArrayNode = DeclarationArrayNode(storage).apply { name = "" }
    private val arrayNode = ArrayNode(storage)

    init {
        description.text = "array"
    }

    override fun createUserInput(layout: ViewGroup) {

        val inflater = LayoutInflater.from(context)
        val nodeLayout = inflater.inflate(R.layout.declaration_array_node_layout, layout)

        val variableNameTexView = nodeLayout.findViewById<EditText>(R.id.variableName)
        variableNameTexView.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                declarationArrayNode.remove()
                if (s.toString() != ""){
                    declarationArrayNode.name = s.toString()
                    arrayNode.name = s.toString()
                    declarationArrayNode.work()
                    description.text = s.toString()
                }
            }
        })
        variableNameTexView.text  = SpannableStringBuilder(declarationArrayNode.name)

        val variableTypeSpinner = nodeLayout.findViewById<Spinner>(R.id.variableType)
        variableTypeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                declarationArrayNode.remove()
                declarationArrayNode.type = DataType.values()[position]
                declarationArrayNode.work()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        for (i in 0 until DataType.values().size){
            if (declarationArrayNode.type == DataType.values()[i]){
                if (declarationArrayNode.type != DataType.UNSPECIFIED && declarationArrayNode.type != DataType.EXEC)
                    variableTypeSpinner.setSelection(i)
                break
            }
        }

        val variableSizeTexView = nodeLayout.findViewById<EditText>(R.id.arraySize)
        variableSizeTexView.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString() != ""){
                    declarationArrayNode.remove()
                    declarationArrayNode.size = s.toString().toInt()
                    declarationArrayNode.work()
                }
            }
        })
        variableSizeTexView.text  = SpannableStringBuilder(declarationArrayNode.size.toString())

        val variableValueTexView = nodeLayout.findViewById<EditText>(R.id.variableValue)
        variableValueTexView.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString() != "") {
                    val input = s.toString().split(",")
//                    for (i in 0 until input.size) {
//                        arrayNode.setArrayIndexValue(i, input[i])
//                    }
                }
            }
        })
        //variableValueTexView.text  = SpannableStringBuilder(declarationVariableNode.value)
    }

}