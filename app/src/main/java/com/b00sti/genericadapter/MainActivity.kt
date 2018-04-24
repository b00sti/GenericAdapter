package com.b00sti.genericadapter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.b00sti.genericadapter.base.AdapterNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val todoList = MutableLiveData<MutableList<TodoItem>>()

    private val adapter = TodoAdapter(object : AdapterNavigator<TodoItem> {
        override fun onItemClicked(item: TodoItem) {
            removeItemFromList(item)
            Toast.makeText(applicationContext, "Item ${item.name} clicked", Toast.LENGTH_SHORT).show()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun removeItemFromList(item: TodoItem) {
        val list = todoList.value?.toMutableList()
        list?.remove(item)
        todoList.postValue(list)
    }

    private fun initUI() {
        todoList.observe(this, Observer { adapter.submitList(it) })
        rvTodoList.layoutManager = LinearLayoutManager(parent)
        rvTodoList.adapter = adapter
        btnAddTodo.setOnClickListener({ addTodo() })
    }

    private fun addTodo() {
        var list = todoList.value?.toMutableList()
        if (list == null) list = mutableListOf()
        list.add(TodoItem("Item" + list.size, System.currentTimeMillis()))
        todoList.postValue(list)
    }
}
