package com.malferma.model

class Ipv4_Subnet {
    var IpAddress : String = ""
    var NetMask : String = ""
    var CIDR : String = ""
    var nrOfHosts : Int = 0
    var nrOfFreeHosts : Int = 0
    var networkAddress : String = ""
    var broadcastAddress : String = ""
    var firstHostAddress = "null"
    var lastHostAddress = "null"
}