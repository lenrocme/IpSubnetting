package com.malferma.model

data class Ipv4Subnet(
    var IpAddress : String = "Ex: 192.168.0.1",
    var NetMask : String = "Ex: 255.255.255.255",
    var CIDR : String = "Ex: /28",
    var NetworkAddress : String = "Ex: 192.168.0.16",
    var NrOfHosts : Int = 0,
    var NrOfFreeHosts : Int = 0,
    var BroadcastAddress : String = "Ex: 192.168.0.31",
    var FirstHostAddress : String= "Ex: 192.168.0.17",
    var LastHostAddress : String = "Ex: 192.168.0.30",
    var RangeOfNetwork :String = "Ex: 192.168.0.17 - 192.168.0.30",
    var ActiveOctet : Int = 0,
)
