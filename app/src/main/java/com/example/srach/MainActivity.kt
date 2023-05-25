package com.example.srach

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.srach.databinding.ActivityMainBinding
import com.example.srach.fieldview.FieldView
import com.example.srach.nodeview.types.math.DivideNodeView

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    var text = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        //setContentView(R.layout.activity_main)

        val fieldView = findViewById<FieldView>(R.id.fieldView)
        val runButton = findViewById<Button>(R.id.button).setOnClickListener {
            fieldView.run()
            val intentCreate = Intent(this, Console::class.java)
            intentCreate.putExtra("action", text)
            startActivity(intentCreate)
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val layout = findViewById<LinearLayout>(R.id.drawer)
        val nodeParamButton = findViewById<Button>(R.id.nodeParamButton).setOnClickListener {
            fieldView.createNodeUserInput(layout)
            drawerLayout.openDrawer(GravityCompat.START)
        }
        drawerLayout.addDrawerListener(object:DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                fieldView.invalidate()
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
    }

    fun onClickAddNodes(view: View){

    }
}
