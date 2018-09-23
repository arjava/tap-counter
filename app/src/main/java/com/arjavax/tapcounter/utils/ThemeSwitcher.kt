package com.arjavax.tapcounter.utils

import android.app.Activity
import android.content.Intent

import com.arjavax.tapcounter.R

/**
 * Created by Arjavax on 22/09/18.
 */

object ThemeSwitcher {
    private var sTheme: Int? = null

    private const val BLACK_DARK_THEME = 0
    private const val RED_DARK_THEME = 1
    private const val BLUE_DARK_THEME = 2
    private const val GREEN_DARK_THEME = 3
    private const val YELLOW_DARK_THEME = 4
    private const val LIGHT_THEME = 5

    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
    }

    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            BLACK_DARK_THEME -> activity.setTheme(R.style.Theme_AppCompat)
            RED_DARK_THEME -> activity.setTheme(R.style.RedDarkTheme)
            BLUE_DARK_THEME -> activity.setTheme(R.style.BlueDarkTheme)
            GREEN_DARK_THEME -> activity.setTheme(R.style.GreenDarkTheme)
            YELLOW_DARK_THEME -> activity.setTheme(R.style.YellowDarkTheme)
            LIGHT_THEME -> activity.setTheme(R.style.Theme_AppCompat_Light)
            else -> activity.setTheme(R.style.Theme_AppCompat)
        }
    }

}
