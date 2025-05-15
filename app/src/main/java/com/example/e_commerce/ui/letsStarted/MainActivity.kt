package com.example.e_commerce.ui.letsStarted

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.e_commerce.R
import com.example.e_commerce.ui.createAccount.CreateAccount
import com.example.e_commerce.ui.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alreadyHaveAccountTxt = findViewById<TextView>(R.id.textView4)
        val arrowIcon = findViewById<ImageView>(R.id.imageView8)
        val letsGetStartedBtn = findViewById<Button>(R.id.button)

        val navigateToLogin = {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
        val navigateToCreateAccount = {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }
        letsGetStartedBtn.setOnClickListener { navigateToCreateAccount() }
        alreadyHaveAccountTxt.setOnClickListener { navigateToLogin() }
        arrowIcon.setOnClickListener { navigateToLogin() }

    }
}
