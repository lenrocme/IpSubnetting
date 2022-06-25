package com.malferma.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * main
 * */
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
var COLOR_BackGround = Color(0xFFECF0F3)

/**
 * neumorphism
 * */
val Light_neumorphism_200 = Color(0xFFECF0F3)
val Light_neumorphism_300 = Color(0xFFD1D9E6)
val Light_neumorphism_400 = Color(0xFF97A7C3)
var Light_neumorphism_500 = Color(0xFF193566)



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
    COLOR_BottomNavMenuNORMAL = Light_neumorphism_300//Color(0xFF999999)
    COLOR_BottomNavMenuSELECTED = Light_neumorphism_400//Color(0xFF039BE5)
    COLOR_BottomNavMenu = Color(0xFFECF0F3)
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