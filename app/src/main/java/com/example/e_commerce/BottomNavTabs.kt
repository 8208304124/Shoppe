package com.example.e_commerce

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavTabs : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_nav_tabs)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Load default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment_home())
            .commit()

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> fragment_home()
                R.id.cart -> fragment_cart()
                R.id.nav_settings -> fragment_wishlist()
                R.id.categories -> fragment_filter()
                R.id.profile -> fragment_profile()
                else -> fragment_home()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            true
        }
    }
}