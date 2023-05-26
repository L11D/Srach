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
    var text = "Hello World!"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        supportFragmentManager.beginTransaction().replace(R.id.contConsole, NewConsole.newInstance()).commit()


        val fieldView = findViewById<FieldView>(R.id.fieldView)

        val runButton = findViewById<Button>(R.id.button).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.contConsole) as? NewConsole
            fragment?.deleteText();

            if (bindingClass.contConsole.visibility == View.GONE){
                bindingClass.contConsole.visibility = View.VISIBLE
                bindingClass.button.text = "CLOSE"
            }
            else{
                bindingClass.contConsole.visibility = View.GONE
                bindingClass.button.text = "RUN"
            }

            fieldView.run()
            fragment?.inputText(text)
            //должны передавать из рана в фрагмент
        }


        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        val layout = findViewById<LinearLayout>(R.id.drawer)
        val layout2 = findViewById<LinearLayout>(R.id.drawer2)

        //fieldView.addNode("AddNode")

        val nodeParamButton = findViewById<Button>(R.id.nodeParamButton).setOnClickListener {

            val layoutParams = bindingClass.drawer.layoutParams as DrawerLayout.LayoutParams

            fieldView.createNodeUserInput(layout)
            drawerLayout.openDrawer(GravityCompat.END)
        }

        val buttonAddNodes = findViewById<Button>(R.id.buttonAddNodes).setOnClickListener{

            layout2.removeAllViews()
            val inflater = LayoutInflater.from(this)
            val nodeLayout = inflater.inflate(R.layout.add_new_nodes, layout2)
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
