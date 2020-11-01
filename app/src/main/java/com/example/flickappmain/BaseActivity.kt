package com.example.flickappmain

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

internal const val FLICK_QUERY = "FLICK_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

open  class BaseActivity : AppCompatActivity() {
    private val tag = "BaseActivity"

    internal fun activateToolbar(enableHome: Boolean) {
        Log.d(tag, ".activateToolbar")

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}