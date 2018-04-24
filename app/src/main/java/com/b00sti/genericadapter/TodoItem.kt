package com.b00sti.genericadapter

import com.b00sti.genericadapter.base.IDiffUtilable

/**
 * Created by b00sti on 23.04.2018
 */
data class TodoItem(val name: String, val timestamp: Long) : IDiffUtilable<TodoItem> {
    override fun areItemsTheSame(oldItem: TodoItem): Boolean = timestamp == oldItem.timestamp
    override fun areContentsTheSame(oldItem: TodoItem): Boolean = this == oldItem
}