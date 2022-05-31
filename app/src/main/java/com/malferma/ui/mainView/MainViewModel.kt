package com.malferma.ui.mainView

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.malferma.model.Ipv4Subnet
import com.malferma.subnetting.Subnetting

class MainViewModel : ViewModel(){
    private val _counter = mutableStateOf(0)
    private val _maxHosts = mutableStateOf(0)
    private val _obj = mutableStateOf(Ipv4Subnet())
    private val _ip = mutableStateOf("")
    private val _netmask = mutableStateOf("")
    val counter: State<Int> = _counter
    val maxHosts: State<Int> = _maxHosts

    val obj: State<Ipv4Subnet> = _obj

    var ip: State<String> = _ip
    var netmask: State<String> = _netmask

    fun tryCounter(){
        _counter.value = _counter.value + 1
    }

    fun test(){
        _maxHosts.value = Subnetting().test()
    }

    fun GetIpAtributs(){
        _obj.value = Subnetting().mainTest()
    }


}