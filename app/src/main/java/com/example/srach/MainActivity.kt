package com.example.srach

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.srach.databinding.ActivityGeneralScreenBinding
import com.example.srach.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  bindingClass : ActivityMainBinding
    var  text = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickStart(view: View){
        //тут идет часть где запускается приложение
        //и начинают перебиратсья ноды

        if (true)//если она принт
        {
            text += ("То что лежит в ноде" + "\n")
        }

        val intentCreate = Intent(this, Console::class.java)
        intentCreate.putExtra("action", text)
        startActivity(intentCreate)
    }

    fun onClickMenu(view: View){
        finish();
    }

    fun onClickOnTest(view: View){
        fieldView.createNodeUserInput(bindingClass.root)
    }

}