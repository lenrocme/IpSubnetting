package com.malferma.ui.navigation

import com.malferma.R


sealed class Screen(
    val route: String,
    val title: String,
    val icon: Int,
){
    object Subnetting: Screen("Subnetting", "Subnetting", R.drawable.ic_calculate_24)
    object Practice: Screen("Practice", "Practice", R.drawable.ic_baseline_coffee_24)
    object QuickAnswer: Screen("QuickAnswer", "QuickAnswer", R.drawable.ic_calculate_24)

    object Items{
        val list = listOf(Subnetting, Practice, QuickAnswer)
    }
}
