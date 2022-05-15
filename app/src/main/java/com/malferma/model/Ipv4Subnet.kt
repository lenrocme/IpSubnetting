package com.malferma.model

data class Ipv4Subnet(
    var IpAddress : String = "",
    var NetMask : String = "",
    var CIDR : String = "",
    var NetworkAddress : String = "",
    var NrOfHosts : Int = 0,
    var NrOfFreeHosts : Int = 0,
    var BroadcastAddress : String = "",
    var FirstHostAddress : String= "",
    var LastHostAddress : String = "",
    var RangeOfNetwork :String = "",
    var ActiveOctet : Int = 0,
)
