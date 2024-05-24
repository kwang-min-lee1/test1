package com.busanit.test1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.test1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var todoAapter : ToDoAdapter
    val todoList = mutableListOf(
        ToDo("오늘의 할일 1"),
        ToDo("오늘의 할일 2"),
        ToDo("오늘의 할일 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnAddTask.setOnClickListener{
            val task = binding.etTask.text.toString()
            if (task.isNotEmpty()) {
                todoAapter.addItem(ToDo(task))
                binding.etTask.text.clear()
            }
        }

    }

    fun setupRecyclerView() {
        todoAapter = ToDoAdapter(todoList) { todo ->
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("task", todo.task)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = todoAapter

        val callback = ItemTouchHelperCallback(todoAapter)
        val touchHelper = ItemTouchHelper (callback)
        touchHelper.attachToRecyclerView(binding.recyclerView)

    }

}