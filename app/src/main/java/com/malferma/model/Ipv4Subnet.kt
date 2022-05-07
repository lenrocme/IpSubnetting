package com.malferma.model

data class Ipv4Subnet(
    var IpAddress : String = "",
    var NetMask : String = "",
    var CIDR : String = "",
    var NetworkId : String = "",
    var NrOfHosts : Int = 0,
    var NrOfFreeHosts : Int = 0,
    var NetworkAddress : String = "",
    var BroadcastAddress : String = "",
    var FirstHostAddress : String= "",
    var LastHostAddress : String = "",
    var RangeOfNetwork :String = "",
    var IndexOfActiveOctet : Int = 0,
)
