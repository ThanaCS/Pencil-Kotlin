package com.example.pencil.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pencil.CanvasView
import com.example.pencil.databinding.ActivityMainBinding
import com.example.pencil.ui.common.viewBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}