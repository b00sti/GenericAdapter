package com.b00sti.genericadapter.base

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup

/**
 * Created by b00sti on 23.04.2018
 */

interface IDiffUtilable<in T : Any> {
    fun areItemsTheSame(oldItem: T): Boolean
    fun areContentsTheSame(oldItem: T): Boolean
}

interface AdapterNavigator<in T : Any> : EmptyNavigator {
    fun onItemClicked(item: T)
}

class AdapterViewModel<V : Any>(val item: V) : BaseViewModel<AdapterNavigator<V>>() {
    fun onItemClicked() = getNavigator().onItemClicked(item)
}

abstract class BaseAdapter<T : IDiffUtilable<T>, R : BaseViewHolder<*, *>>() : ListAdapter<T, R>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = newItem.areItemsTheSame(oldItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = newItem.areContentsTheSame(oldItem)
}) {

    abstract fun getViewHolder(parent: ViewGroup): R
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): R = getViewHolder(parent)
    override fun onBindViewHolder(holder: R, position: Int) = holder.onBind(position)

}