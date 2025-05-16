package com.example.e_commerce.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e_commerce.R
import com.example.e_commerce.ui.passwordrecovery.password_recovery

class EnterPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_password)
        val forgotText = findViewById<TextView>(R.id.textView9)

        forgotText.setOnClickListener {
            val intent = Intent(this, password_recovery::class.java)
            startActivity(intent)
        }

    }
}