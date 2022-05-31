package com.malferma.ui.mainView

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.malferma.model.Ipv4Subnet
import com.malferma.subnetting.Subnetting
import com.malferma.validator.Ipv4Validator

class MainViewModel(
                    private val ip4Validator: Ipv4Validator = Ipv4Validator(),
                    ) : ViewModel(){
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

    fun test(ip: String, netmask: String): String {
        if(ip4Validator.validateUserInput(ip)){
            _obj.value = Subnetting().SubnettingByOnlineForm(ip)
            return _obj.value.NetMask
        }
        return netmask
        //return Ipv4Subnet()
    }

    fun SetIp(ip: String, netmask: String): String{
        if(ip4Validator.validateUserInput(ip)){
            _obj.value = Subnetting().SubnettingByOnlineForm(ip)
            return _obj.value.NetMask
        }
        return netmask
    }

    /**
     * @return When validation is passed, the new ip with cidr int together format calculated by netmask or cidr*/
    fun SetIpByNetmaskOrCidr(ip:String, netmaskOrCidr: String): String{
        if(ip4Validator.validateUserInputByNetmask(ip, netmaskOrCidr)){
            _obj.value = Subnetting().SubnetingByNetmask(ip, netmaskOrCidr)
            return "${_obj.value.IpAddress}/${_obj.value.CIDR}"
        }
        if(ip4Validator.validateUserInputByCidr(ip, netmaskOrCidr)){
            _obj.value = Subnetting().SubnettingByCidr(ip, netmaskOrCidr)
            return "${_obj.value.IpAddress}/${_obj.value.CIDR}"
        }
        return ip
    }

    fun GetIpAtributs(){
        _obj.value = Subnetting().mainTest()
    }


}