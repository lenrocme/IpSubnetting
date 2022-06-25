package com.malferma.ui.navigation

import com.malferma.R


sealed class Screen(
    val route: String,
    //val title: String,
    val icon: Int,
){
    object Subnetting: Screen("Subnetting", R.drawable.ic_calculate_24)
    object Exercise: Screen("Exercise", R.drawable.ic_exercise_32)
    object QuickAnswer: Screen("Quick", R.drawable.ic_quick_answer)
    object Chart: Screen("Chart", R.drawable.ic_chart_32)

    object Items{
        val list = listOf(Subnetting, Exercise, QuickAnswer, Chart)
    }
}
