package com.example.srach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.srach.databinding.ActivityConsoleBinding

class Console : AppCompatActivity() {
    lateinit var  bindingClass : ActivityConsoleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityConsoleBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val message = intent.getStringExtra("action")
        val console = bindingClass.console.text
        bindingClass.console.text = message

    }

    fun onClickExitConsole(view: View){
        finish()
    }

}