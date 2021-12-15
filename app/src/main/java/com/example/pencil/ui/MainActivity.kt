package com.example.pencil.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pencil.CanvasView
import com.example.pencil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.layout.addView(CanvasView(this))

    }

}