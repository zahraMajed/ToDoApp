package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_item.view.*

class RecyclerAdpater (val toDoList:ArrayList<toDo>):RecyclerView.Adapter<RecyclerAdpater.itemViewHolder> () {
    class itemViewHolder (itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.view_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val task=toDoList[position].text
        var checked=toDoList[position].checked

        holder.itemView.apply {
            tvItem.text=task
            cbItem.isChecked=checked

            cbItem.setOnCheckedChangeListener(){
                _, isChecked ->
                if(isChecked){
                    tvItem.setTextColor(Color.GRAY)
                }else{
                    tvItem.setTextColor(Color.BLACK)
                }
                toDoList[position].checked = !toDoList[position].checked
            }//
        }
    }

    override fun getItemCount(): Int = toDoList.size

    fun deleteItems(){
        toDoList.removeAll{ task -> task.checked }
    }

}