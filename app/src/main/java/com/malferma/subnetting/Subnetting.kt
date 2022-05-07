package com.malferma.subnetting

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
        this.ipv4.SubnetMask = "255.255.192.0"
        return calcMaxNrOfHost(this.ipv4.CIDR)
    }

    fun mainTest(): Ipv4_Subnet {
        this.ipv4.IpAddress = "192.168.0.1"
        this.ipv4.CIDR = "27"
        this.ipv4.SubnetMask = "255.255.192.0"
        this.ipv4.nrOfHosts = calcMaxNrOfHost(this.ipv4.CIDR)
        this.ipv4.nrOfFreeHosts = calcMaxNrOfFreeHost(this.ipv4.nrOfHosts)
        this.ipv4.SubnetMask = calcSubnetFromCidr(this.ipv4.CIDR)
        return ipv4
    }

    /**
     * Transform CIDR from user imputed form to an Integer
     * @param networkCidr The CIDR of the Network
     * @return The Integer value of the CIDR*/
    private fun getIntOfTheCidr(networkCidr : String): Int{
        return if (networkCidr.contains('/'))
            networkCidr.drop(1).toInt()
        else
            networkCidr.toInt()
    }

    /**
     * Calculate maximal nr of hosts in the network
     * @param networkCidr the CIDR of the IPv4
     * @return maximal nr of hosts*/
    private fun calcMaxNrOfHost(networkCidr : String): Int {
        val cidr = getIntOfTheCidr(networkCidr)
        return if (cidr != 0)
            2.0.pow(cidr.toDouble()).toInt()
        else
            0
    }

    /**
     * Calculate maximal nr of FREE hosts in the network
     * @param maxNrOfHost maximal available nr of Hosts in the network
     * @return maximal nr of FREE hosts*/
    private fun calcMaxNrOfFreeHost(maxNrOfHost : Int): Int {
        return maxNrOfHost - 2
    }

    /**
     * Find the subnet mask of network from the CIDR
     * @param cidr The CIDR of the network
     * @return The Subnet mask of the network*/
    private fun calcSubnetFromCidr(cidr : String): String{
        val formattedCidr = getIntOfTheCidr(cidr)
        val nrOfFullOctets = formattedCidr/8
        val nrOfEmptyOctet = 4 - nrOfFullOctets
        val octetBytes = 8 - (formattedCidr - nrOfFullOctets * 8)
        val emptyBits = 2.0.pow(octetBytes.toDouble()).toInt()
        val octetBits = 256 - emptyBits
        return "255.".repeat(nrOfFullOctets) + octetBits+ ".0".repeat(nrOfEmptyOctet-1)
    }
}