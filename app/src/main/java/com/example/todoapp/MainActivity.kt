package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var btnFA: FloatingActionButton
    lateinit var taskList: ArrayList<toDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskList= arrayListOf()
        rv_main.adapter=RecyclerAdpater(taskList)
       rv_main.layoutManager=LinearLayoutManager(this)

        btnFA=findViewById(R.id.btnFA)
       btnFA.setOnClickListener(){
            AlertDialog()
       }

    }//end onCreate()

    fun AlertDialog(){
        val dialogBuilder=AlertDialog.Builder(this)

        val userItem=EditText(this)
        userItem.hint="Enter to-do item"


        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
            Dialog,id->
            taskList.add(toDo(userItem.text.toString()))
            rv_main.adapter?.notifyDataSetChanged()
        })

        dialogBuilder.setNegativeButton("CANCEL ", DialogInterface.OnClickListener {
                Dialog,id-> Dialog.cancel()
        })

        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(userItem)
        alert.show()
    }//end AlertDialog()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miDelete -> {
                var itemToDelet=0
                for ( task in taskList){
                    if (task.checked){
                        itemToDelet++
                }
            }
                if (itemToDelet >0){
                    Toast.makeText(this, "$itemToDelet items deleted", Toast.LENGTH_LONG).show()
                }else
                    Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
                RecyclerAdpater(taskList).deleteItems()
                rv_main.adapter?.notifyDataSetChanged()
            }

        }
        return super.onOptionsItemSelected(item)
    }

} //end class