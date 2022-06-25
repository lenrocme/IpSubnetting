package com.malferma.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * main
 * */
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


/**
 * Bottom nav bar
 * */
var COLOR_BottomNavMenuNORMAL = Color(0xffEDEFF4)
var COLOR_BottomNavMenuSELECTED = Color(0xff496DE2)
var COLOR_BottomNavMenu = Color(0xff496DE2)


/**
 * Set custom color for LIGHT theme
 * */
fun THEME_LIGHT(){
    /**
     * Bottom nav bar
     * */
    COLOR_BottomNavMenuNORMAL = Color(0xFF999999)
    COLOR_BottomNavMenuSELECTED = Color(0xFF039BE5)
    COLOR_BottomNavMenu = Color(0xFFE7E7E7)
}


/**
 * Set custom color for DARK theme
 * */
fun THEME_DARK(){
    /**
     * Bottom nav bar
     * */
    COLOR_BottomNavMenuNORMAL = Color(0xFF686868)
    COLOR_BottomNavMenuSELECTED = Color(0xFF617FE0)
    COLOR_BottomNavMenu = Color(0xFF2E2E2E)
}