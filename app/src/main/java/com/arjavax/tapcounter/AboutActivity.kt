package com.arjavax.tapcounter

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by Arjavax on 23/09/18.
 */

class AboutActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        valueEffectBy.text = "ZapSplat.com\nSoundEffectsPlus"
        valueLibrary.text = "Boast"
        myBlog.movementMethod = LinkMovementMethod.getInstance()
        versionName.text = "version "+BuildConfig.VERSION_NAME

    }

}