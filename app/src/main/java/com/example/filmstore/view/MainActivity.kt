package com.example.filmstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.filmstore.R
import com.example.filmstore.R.id.*
import com.example.filmstore.databinding.ActivityMainBinding
import com.example.filmstore.databinding.FragmentMainBinding
import com.example.filmstore.view.DetailsFragment.Companion.newInstance
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.Exception
import java.security.cert.Extension

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var bindingGeneral: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingGeneral = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, ListFragment.newInstance())
                .commitNow()

        }


        bindingGeneral!!.bottomNavigationView.also{bottomNavigation ->
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    films -> {
                        supportFragmentManager.beginTransaction()
                            .replace(container_general, ListFragment.newInstance())
                            .commitNow()
                    }
                    library -> {
                        supportFragmentManager.beginTransaction()
                            .replace(container_general, LibraryFragment.newInstance())
                            .commitNow()
                    }
                }
                true
            }
        }

    }
}
