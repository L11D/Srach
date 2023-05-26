package com.example.srach

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.srach.databinding.ActivityMainBinding
import com.example.srach.fieldview.FieldView
import com.example.srach.nodeview.types.math.AddNodeView
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

        //fieldView.addNode("AddNode")

        val nodeParamButton = findViewById<Button>(R.id.nodeParamButton).setOnClickListener {

            val layoutParams = bindingClass.drawer.layoutParams as DrawerLayout.LayoutParams
            layoutParams.gravity = GravityCompat.END
            bindingClass.drawer.layoutParams = layoutParams
            //ТУТ ПРОБЛЕМЫ В РЕЗКОСТИ ИЗМЕНЕНИЯ СТОРОНЫ ВЫХОДА

            fieldView.createNodeUserInput(layout)
            drawerLayout.openDrawer(GravityCompat.END)
        }

        val buttonAddNodes = findViewById<Button>(R.id.buttonAddNodes).setOnClickListener{

            val layoutParams = bindingClass.drawer.layoutParams as DrawerLayout.LayoutParams
            layoutParams.gravity = GravityCompat.START
            bindingClass.drawer.layoutParams = layoutParams
            //ТУТ ПРОБЛЕМЫ В РЕЗКОСТИ ИЗМЕНЕНИЯ СТОРОНЫ ВЫХОДА

            layout.removeAllViews()
            val inflater = LayoutInflater.from(this)
            val nodeLayout = inflater.inflate(R.layout.add_new_nodes, layout)
            drawerLayout.openDrawer(GravityCompat.START)

            val addSelectedNode = findViewById<Button>(R.id.addSelectedNode).setOnClickListener{
                var spinner2 =findViewById<Spinner>(R.id.spinner2)
                fieldView.addNode(spinner2.getSelectedItem().toString())
                fieldView.invalidate()
            }
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

}
