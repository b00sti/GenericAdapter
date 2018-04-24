package com.b00sti.genericadapter.base

import android.arch.lifecycle.ViewModel

/**
 * Created by b00sti on 23.04.2018
 */
interface EmptyNavigator

abstract class BaseViewModel<N : EmptyNavigator> : ViewModel() {
    private lateinit var mNavigator: N

    fun getNavigator(): N = mNavigator

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

}