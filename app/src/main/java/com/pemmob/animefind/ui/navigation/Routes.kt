package com.pemmob.animefind.ui.navigation

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{malId}"

    fun createDetailRoute(malId: Int): String {
        return "detail/$malId"
    }
}