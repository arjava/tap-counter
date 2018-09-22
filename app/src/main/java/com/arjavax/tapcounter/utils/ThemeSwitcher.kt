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
    private const val GREEN_DARK_THEME = 1
    private const val YELLOW_DARK_THEME = 2

    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
    }

    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            BLACK_DARK_THEME -> activity.setTheme(R.style.BlackDarkTheme)
            GREEN_DARK_THEME -> activity.setTheme(R.style.GreenDarkTheme)
            YELLOW_DARK_THEME -> activity.setTheme(R.style.YellowDarkTheme)
            else -> activity.setTheme(R.style.BlackDarkTheme)
        }
    }

}
