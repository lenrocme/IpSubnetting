package com.malferma.subnetting

import com.malferma.model.Ipv4Subnet
import com.malferma.model.Ipv6Subnet
import kotlin.math.pow

/**
 *
 * **/
class Subnetting {
    private var ipv4 : Ipv4Subnet = Ipv4Subnet()
    private var ipv6 : Ipv6Subnet = Ipv6Subnet()

    constructor()

    constructor(ipv4 : Ipv4Subnet)

    constructor(ipv6 : Ipv6Subnet)

    fun test(): Int {
        this.ipv4.IpAddress = "192.168.0.1"
        this.ipv4.CIDR = "/27"
        this.ipv4.NetMask = "255.255.192.0"
        return calcMaxNrOfHost(this.ipv4.CIDR)
    }

    fun mainTest(): Ipv4Subnet = with(this.ipv4){
        IpAddress = "193.55.144.25"
        NetMask = "255.255.255.248"
        CIDR = calcCiderFromSubnet(NetMask).toString()
        NrOfHosts = calcMaxNrOfHost(CIDR)
        NrOfFreeHosts = calcMaxNrOfFreeHost(NrOfHosts)
        NetMask = calcSubnetFromCidr(CIDR)
        IndexOfActiveOctet = getActiveOctetByNetmask(NetMask)
        //CIDR = calcCiderFromSubnet(NetMask).toString()
        NetworkId = calcNetworkId(IpAddress, NetMask)
        FirstHostAddress = calcFirstHostIpAddress(NetworkId)
        BroadcastAddress = calcBroadCasOfIpAddress(NetworkId, NetMask, IndexOfActiveOctet)
        return ipv4
    }

    /**
     * Transform CIDR from user imputed form to an Integer
     * @param networkCidr The CIDR of the Network
     * @return The Integer value of the CIDR
     */
    private fun getIntOfTheCidr(networkCidr : String): Int{
        return if (networkCidr.contains('/'))
            networkCidr.drop(1).toInt()
        else
            networkCidr.toInt()
    }

    /**
     * Calculate maximal nr of hosts in the network
     * @param networkCidr the CIDR of the IPv4
     * @return maximal nr of hosts
     */
    private fun calcMaxNrOfHost(networkCidr : String): Int {
        val cidr = getIntOfTheCidr(networkCidr)
        return if (cidr != 0)
            2.0.pow(32 - cidr.toDouble()).toInt()
        else
            0
    }

    /**
     * Calculate maximal nr of FREE hosts in the network
     * @param maxNrOfHost maximal available nr of Hosts in the network
     * @return maximal nr of FREE hosts
     */
    private fun calcMaxNrOfFreeHost(maxNrOfHost : Int): Int {
        return maxNrOfHost - 2
    }

    /**
     * Find the SubNetmask of network from the CIDR
     * @param cidr The CIDR of the network
     * @return The SubNetmask of the network
     */
    private fun calcSubnetFromCidr(cidr : String): String{
        val formattedCidr = this.getIntOfTheCidr(cidr)
        val nrOfFullOctets = formattedCidr/8
        val nrOfEmptyOctet = 4 - nrOfFullOctets
        val octetBytes = 8 - (formattedCidr - nrOfFullOctets * 8)
        val emptyBits = 2.0.pow(octetBytes.toDouble()).toInt()
        val octetBits = 256 - emptyBits
        return "${"255.".repeat(nrOfFullOctets)}$octetBits${".0".repeat(nrOfEmptyOctet-1)}"
    }

    /**
     * Calculate the CIDR of the Network from the SubNetmask
     * @param subnetMask The SubNetmask of the Network
     * @return The CIDR of the Network
     */
    private fun calcCiderFromSubnet(subnetMask: String): Int{
        var countFullOctet = 0
        val octetArr = subnetMask.split('.')
        octetArr.forEach {
            if (it == "255")
                countFullOctet += 1
        }
        val bitsOfActiveOctet = octetArr[countFullOctet].toInt()
        val emptyBits = 256 - bitsOfActiveOctet
        var bytesOfActiveOctet = 0
        for (bytes in 1..8) {
            if (2.0.pow(bytes.toDouble()).toInt() == emptyBits) {
                bytesOfActiveOctet = 8 - bytes
                break
            }
        }
        return bytesOfActiveOctet + countFullOctet * 8   // The CIDR value of the Network
    }

    /**
     * Get the active octet by CIDR
     * @param networkCidr The CIDR of the Network
     * @return The nr of the active octet of the Network
     */
    private fun getActiveOctetByCidr(networkCidr: Int): Int = networkCidr / 8 + 1

    /**
     * Get the active octet by SubNetmask
     * @param subnetMask The SubNetmask of the Network
     * @return The nr of the active octet of the Network
     */
    private fun getActiveOctetByNetmask(subnetMask: String): Int {
        var counterIndex = 1
        subnetMask.split('.').forEach{
            if(it == "255")
                counterIndex++
        }
        return counterIndex
    }

    /**
     * Calculate Network ID by CIDR
     * @param ipAddress The IP address from the searched Network
     * @param networkCidr The CIDR of the Network
     * @param indexOfActiveOctet The index of the active octet of the Network
     * @return The Network ID
     */
    private fun calcNetworkId(ipAddress: String,
                              networkCidr: Int,
                              indexOfActiveOctet: Int = this.getActiveOctetByCidr(networkCidr)): String{

        return this.calcNetworkId(ipAddress,
            this.calcSubnetFromCidr(networkCidr.toString()),
            indexOfActiveOctet)
    }

    /**
     * Calculate Network ID by SubnetMask
     * @param ipAddress The IP address from the searched Network
     * @param subnetMask The SubnetMask of the Network
     * @param indexOfActiveOctet The index of the active octet of the Network
     * @return The Network ID
     */
    private fun calcNetworkId(ipAddress: String,
                              subnetMask: String,
                              indexOfActiveOctet: Int = this.getActiveOctetByNetmask(subnetMask),): String{

        val ipActiveOctet = ipAddress.split('.')[indexOfActiveOctet - 1].toInt()
        val subnetActiveOctet = subnetMask.split('.')[indexOfActiveOctet - 1].toInt()
        val jump = 256 - subnetActiveOctet
        val networkIdActiveOctet = ipActiveOctet / jump * jump

        val networkId = ipAddress.split('.').toMutableList()
        networkId[indexOfActiveOctet - 1] = networkIdActiveOctet.toString()
        for(i in indexOfActiveOctet..3) networkId[i] = "0"

        return this.toIpFormat(networkId)
    }

    /**
     * Find the first Host as IP address of the Network
     * @param networkId The Network ID
     * @return The first Host of the Network
     */
    private fun calcFirstHostIpAddress(networkId: String): String{
        val firstHostActiveOctet = networkId.split('.')[3].toInt() + 1      // with index 3 is changed always last octet
        val subnetAttribute = this.setActiveOctetToAttribute(3, firstHostActiveOctet.toString(), networkId)
        return this.toIpFormat(subnetAttribute)
    }

    /**
     * Find the Broadcast of the IP address of the Network
     * @param networkId The Network ID of the Network
     * @param jump The jump
     * @param subnetMask The subnet mask
     * @param indexOfActiveOctet The index of active Octet
     * @return The Broadcast Address of the Network
     */
    private fun calcBroadCasOfIpAddress(networkId: String,
                                        subnetMask: String,
                                        indexOfActiveOctet: Int = this.getActiveOctetByNetmask(subnetMask),): String{

        val networkIdArr = networkId.split('.')
        val subnetActiveOctet = subnetMask.split('.')[indexOfActiveOctet - 1].toInt()
        var networkIdOctet = networkId.split('.')[indexOfActiveOctet - 1].toInt()
        val jump = 256 - subnetActiveOctet
        networkIdOctet = networkIdOctet + jump - 1

        val stringOf = when(indexOfActiveOctet){
            1 -> "$networkIdOctet.255.255.255"
            2 -> "${networkIdArr[0]}.$networkIdOctet.255.255"
            3 -> "${networkIdArr[0]}.${networkIdArr[1]}.$networkIdOctet.255"
            else -> "${networkIdArr[0]}.${networkIdArr[1]}.${networkIdArr[2]}.$networkIdOctet"
        }
        return stringOf
    }

    /**
     * Set new value of the IP attribute into selected Octet
     * @param indexOfOctet The index of the octet, which it's to change
     * @param setNewValue The new value to be set into selected octet
     * @param subnetIpAttribute The subnet attribute
     * @return The list with Octets of subnet attribute
     */
    private fun setActiveOctetToAttribute(indexOfOctet: Int,
                                          setNewValue: String,
                                          subnetIpAttribute: String,) : List<String>{

        val subnetAttribute = subnetIpAttribute.split('.').toMutableList()
        subnetAttribute[indexOfOctet] = setNewValue
        return subnetAttribute
    }

    /**
     * Format the list of value of the subnet attribute to the string value
     * @param arr The list with octets
     * @return The transformed to the string arr of IP attribute
     */
    private fun toIpFormat(arr: List<String>): String =
        "${arr[0]}.${arr[1]}.${arr[2]}.${arr[3]}"

}