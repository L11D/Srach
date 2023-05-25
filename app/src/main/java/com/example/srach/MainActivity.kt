package com.example.srach

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.srach.databinding.ActivityGeneralScreenBinding
import com.example.srach.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  bindingClass : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickStart(view: View){
        val intentCreate = Intent(this, Console::class.java)
        intentCreate.putExtra("action", "Hello World!\nIts working!\nYes!\nA\n" +
                "Agfdjgdfogjklfghdlkgfkgsdkjgfdkgjsdfgjldjgkldfjgjgldjgldfjglfdjlgkdjlftgkjdlgjdlkgjdflgjdlfgjldfjgdjgldfjgldfjgldfjglkdfjgldjflgdjlkgjdolgjdflgjdolgjdfoljfgd\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A\n" +
                "A")
        //тут надо подумать что передавать
        startActivity(intentCreate)
    }

    fun onClickMenu(view: View){
        finish();
    }

}