package com.example.filmstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmstore.R
import com.example.filmstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, ListFragment.newInstance())
                .commitNow()
        }
    }
}