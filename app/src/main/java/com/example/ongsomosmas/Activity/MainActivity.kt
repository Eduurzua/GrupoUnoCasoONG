package com.example.ongsomosmas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ongsomosmas.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_OngSomosMas)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}