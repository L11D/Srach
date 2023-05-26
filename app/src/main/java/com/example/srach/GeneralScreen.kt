package com.example.srach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.srach.databinding.ActivityGeneralScreenBinding
import com.example.srach.databinding.ActivityMainBinding

class GeneralScreen : AppCompatActivity() {
    lateinit var  bindingClass : ActivityGeneralScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityGeneralScreenBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickCreate(view: View){
        val intentCreate = Intent(this, MainActivity::class.java)
        intentCreate.putExtra("action", "create")
        startActivity(intentCreate)
    }

    fun onClickEnter(view: View){
        val intentCreate = Intent(this, MainActivity::class.java)
        intentCreate.putExtra("action", "enter")
        startActivity(intentCreate)
    }
}