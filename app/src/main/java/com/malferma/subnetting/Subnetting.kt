package com.malferma.subnetting

import android.util.Log
import com.malferma.model.Ipv4_Subnet
import com.malferma.model.Ipv6_Subnet
import kotlin.math.pow

/**
 *
 * **/
class Subnetting {
    private var ipv4 : Ipv4_Subnet = Ipv4_Subnet()
    private var ipv6 : Ipv6_Subnet = Ipv6_Subnet()

    constructor()

    constructor(ipv4 : Ipv4_Subnet)

    constructor(ipv6 : Ipv6_Subnet)

    fun test(): Int {
        this.ipv4.IpAddress = "192.168.0.1"
        this.ipv4.CIDR = "/27"
        this.ipv4.Subnet = "255.255.192.0"
        return calcMaxNrOfHost()
    }

    fun test2(): Ipv4_Subnet {
        this.ipv4.IpAddress = "192.168.0.1"
        this.ipv4.CIDR = "5"
        this.ipv4.Subnet = "255.255.192.0"
        this.ipv4.nrOfHosts = calcMaxNrOfHost()
        this.ipv4.nrOfFreeHosts = calcMaxNrOfFreeHost()
        return ipv4
    }

    /**
     * Calculate maximal nr of hosts in the network
     * @return maximal nr of hosts*/
    private fun calcMaxNrOfHost(): Int {
        val cidr = if (this.ipv4.CIDR.contains('/'))
                this.ipv4.CIDR.drop(1).toInt()
            else
                this.ipv4.CIDR.toInt()
        return if (cidr != 0)
            2.0.pow(cidr.toDouble()).toInt()
        else
            0
    }

    /**
     * Calculate maximal nr of FREE hosts in the network
     * @return maximal nr of FREE hosts*/
    private fun calcMaxNrOfFreeHost(): Int {
        return this.ipv4.nrOfHosts - 2
    }


}