package com.malferma.validator

class Ipv4Validator {
    var ipv4Input: String = ""


    private fun deleteWhiteSpace(inputValue: String): String =
        inputValue.replace("\\s".toRegex(), "")


    private fun validateSubnet(subnet: String): Boolean{
        val subnetArr: List<String> = subnet.split('.')
        if (subnetArr.count() != 4)
            return false

        if (!this.validateOctetsFormat(subnetArr))
            return false
        return true
    }

    /**
     * Check if netmask has right format
     * @param subnetList The list with octets of the netmask
     * @return The True value, when format of the netmask is right
     */
    private fun validateOctetsFormat(subnetList: List<String>): Boolean{
        // validate each octet
        if(!this.validateOctets(subnetList))
            return false

        var index: Int = 0
        if(subnetList[0] == "0")
            return false
        // check active octet, get his index
        for(i in 0..3){
            if(subnetList[i] != "255")
                break
            index++
        }
        // after active octet is funded next octets have to be equal to 0
        for(i in ++index..3){
            if(subnetList[i] != "0")
                return false
        }
        return true
    }

    /**
     * Proof each octet of the netmask
     * @param subnetList The list with octets of the netmask
     * @return The True value, when each octet of the netmask are valid one
     */
    private fun validateOctets(subnetList: List<String>): Boolean{
        val exSubnetOctet: Array<String> = arrayOf("255", "254", "252", "248", "240", "224", "192", "128", "0")
        subnetList.forEach {
            if (!exSubnetOctet.contains(it))
                return false
        }
        return true
    }

    private fun validateIpv4Address(ipv4Address: String): Boolean{
        return true
    }

    private fun validateCidr(cidrSufix: String): Boolean{
        return true
    }
}