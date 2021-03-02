package com.kml.parliamentapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kml.parliamentapp.ParliamentApplication
import com.kml.parliamentapp.R

//Activity that hosts all of the fragments in the app

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}