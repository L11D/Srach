package com.example.srach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.srach.fieldview.FieldView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun bindConsoleTo(fieldView:FieldView){
        fieldView.bindConsole(findViewById<TextView>(R.id.textView2))
    }
}