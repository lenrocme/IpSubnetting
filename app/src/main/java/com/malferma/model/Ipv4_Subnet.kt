package com.malferma.model

class Ipv4_Subnet {
    lateinit var IpAddress : String
    lateinit var Subnet : String
    lateinit var CIDR : String
    var nrOfHosts : Int = 0
    var nrOfFreeHosts : Int = nrOfHosts - 2
    lateinit var networkAddress : String
    lateinit var broadcastAddress : String
    var firstHostAddress = null
    var lastHostAddress = null
}