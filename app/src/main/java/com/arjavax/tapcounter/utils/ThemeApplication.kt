package com.arjavax.tapcounter.utils

import android.annotation.SuppressLint
import android.app.Application

@SuppressLint("Registered")
/**
 * Created by Arjavax on 22/09/18.
 */

class ThemeApplication : Application() {

    companion object {
        var currentPosition: Int? = null
    }

}
