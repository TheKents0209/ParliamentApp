package com.kml.parliamentapp.ui.base

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Activity that hosts all of the fragments in the app
* */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kml.parliamentapp.ParliamentApplication
import com.kml.parliamentapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}