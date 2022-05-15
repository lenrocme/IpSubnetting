package com.malferma.validator

import java.lang.NumberFormatException

class Ipv4Validator {
    var ipv4Input: String = ""


    private fun deleteWhiteSpace(inputValue: String): String =
        inputValue.replace("\\s".toRegex(), "")

    private fun validateSubnet(inputNetmask: String): Boolean{
        val netmask: String = inputNetmask.replace("\\s".toRegex(), "")
        val subnetArr: List<String> = this.deleteWhiteSpace(netmask).split('.')
        if (subnetArr.count() != 4)
            return false

        if (!this.validateOctetsFormat(subnetArr))
            return false
        return true
    }

    /**
     * Check if netmask has right format
     * @param netmask The list with octets of the netmask
     * @return The True value, when format of the netmask is right
     */
    private fun validateOctetsFormat(netmask: List<String>): Boolean{
        // validate each octet
        if(!this.validateOctets(netmask))
            return false

        var index: Int = 0
        if(netmask[0] == "0")
            return false
        // check active octet, get his index
        for(i in 0..3){
            if(netmask[i] != "255")
                break
            index++
        }
        // after active octet is funded next octets have to be equal to 0
        for(i in ++index..3){
            if(netmask[i] != "0")
                return false
        }
        return true
    }

    /**
     * Proof each octet of the netmask
     * @param netmask The list with octets of the netmask
     * @return The True value, when each octet of the netmask are valid one
     */
    private fun validateOctets(netmask: List<String>): Boolean{
        val exSubnetOctet: Array<String> = arrayOf("255", "254", "252", "248", "240", "224", "192", "128", "0")
        netmask.forEach {
            if (!exSubnetOctet.contains(it))
                return false
        }
        return true
    }

    /**
     * Validate the format of the Ip address
     * @param ipv4Address The Ip Address
     * @return The True value, when the imputed Ip address is valid one
     */
    private fun validateIpv4Address(ipv4Address: String): Boolean{
        val ipv4: String = ipv4Address.replace("\\s".toRegex(), "")
        val ipv4List: List<String> = this.deleteWhiteSpace(ipv4).split('.')
        if (ipv4List.count() != 4)
            return false
        try{
            for (octet in ipv4List)
                if(octet.toInt() > 255 || octet.toInt() < 0)
                    return false
        }
        catch (e: NumberFormatException) {
            return false
        }
        return true
    }

    private fun validateCidr(cidrSufix: String): Boolean{
        return true
    }
}