package com.example.unsplash.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unsplash.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Unsplash)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
