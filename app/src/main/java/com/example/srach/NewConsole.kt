package com.example.srach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srach.databinding.ActivityMainBinding
import com.example.srach.databinding.FragmentNewConsoleBinding

class NewConsole : Fragment() {
    lateinit var bindingClass: FragmentNewConsoleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentNewConsoleBinding.inflate(inflater)
        return bindingClass.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewConsole()
    }

    fun inputText(text: String){
        bindingClass.console.text = bindingClass.console.text.toString() +  text + "\n"
    }

    fun deleteText(){
        bindingClass.console.text = ""
    }
}