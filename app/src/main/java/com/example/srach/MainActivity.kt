package com.example.srach

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.srach.fieldview.FieldView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fieldView = findViewById<FieldView>(R.id.fieldView)
        val runButton = findViewById<Button>(R.id.button)
        runButton.setOnClickListener{
            fieldView.run()
        }
    }
}

