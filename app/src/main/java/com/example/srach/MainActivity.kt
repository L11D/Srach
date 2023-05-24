package com.example.srach

import android.os.Bundle
import android.view.ContextMenu
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import com.example.srach.fieldview.FieldView
import java.io.PrintStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fieldView = findViewById<FieldView>(R.id.fieldView)
        val runButton = findViewById<Button>(R.id.button)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        val layout = findViewById<LinearLayout>(R.id.drawer)
        val a = Button(this).apply{text = "dddd"}


        val inflater = LayoutInflater.from(this)
        val buttonLayout = inflater.inflate(R.layout.button_layout, null)
        layout.addView(buttonLayout)

        runButton.setOnClickListener {
            //fieldView.run()
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }
}
