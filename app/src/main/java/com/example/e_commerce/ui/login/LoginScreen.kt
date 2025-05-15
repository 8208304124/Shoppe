package com.example.e_commerce.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        val button = findViewById<Button>(R.id.button2)
        button.backgroundTintList = null
        button.setOnClickListener {
            val intent = Intent(this, EnterPassword::class.java)
            startActivity(intent)
        }
    }
}