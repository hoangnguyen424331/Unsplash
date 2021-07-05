package com.example.unsplash.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unsplash.R
import com.example.unsplash.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Unsplash)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}
