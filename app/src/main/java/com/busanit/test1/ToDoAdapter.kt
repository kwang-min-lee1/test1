package com.busanit.test1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ToDoAdapter (
    val items: MutableList<ToDo>,
    val clickListener: (ToDo) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>(), ItemTouchHelperCallback.ItemTouchHelperAdapter{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount(): Int = items.size

    fun addItem(todo: ToDo) {
        items.add(todo)
        notifyItemInserted(items.size -1)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTextView: TextView = itemView.findViewById(R.id.tvTask)

        fun bind(todo: ToDo, clickListener: (ToDo) -> Unit) {
            taskTextView.text = todo.task
            itemView.setOnClickListener{clickListener(todo)}
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val fromItem = items.removeAt(fromPosition)
        items.add(if (toPosition > fromPosition) toPosition -1 else toPosition, fromItem)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(positon: Int) {
        items.removeAt(positon)
        notifyItemRemoved(positon)
    }

}