package com.example.ongsomosmas.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ongsomosmas.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_OngSomosMas)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

