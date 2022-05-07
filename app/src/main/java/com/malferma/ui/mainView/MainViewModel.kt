package com.malferma.ui.mainView

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val _counter = mutableStateOf(0)
    val counter: State<Int> = _counter

    fun tryCounter(){
        _counter.value = _counter.value + 1
    }


}