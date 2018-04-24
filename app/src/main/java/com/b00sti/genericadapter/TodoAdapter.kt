package com.b00sti.genericadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.b00sti.genericadapter.base.*
import com.b00sti.genericadapter.databinding.ItemTodoBinding

class TodoAdapter(val callback: AdapterNavigator<TodoItem>) : BaseAdapter<TodoItem, TodoAdapter.TodoHolder>() {
    override fun getViewHolder(parent: ViewGroup): TodoHolder = TodoHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class TodoHolder(binding: ItemTodoBinding) : BaseViewHolder<ItemTodoBinding, AdapterViewModel<TodoItem>>(binding), AdapterNavigator<TodoItem> {

        override fun onItemClicked(item: TodoItem) = callback.onItemClicked(item)

        override fun getViewModel(position: Int): AdapterViewModel<TodoItem> {
            val viewModel = AdapterViewModel(getItem(adapterPosition))
            viewModel.setNavigator(this)
            return viewModel
        }

        override fun getBindingVariable(): Int = BR.vm
    }
}