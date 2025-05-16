package com.example.e_commerce.ui.passwordrecovery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R
import com.example.e_commerce.ui.login.EnterPassword
import com.example.e_commerce.ui.smsPasswordRecovery.sms_password_recovery

class password_recovery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password_recovery)
        val button = findViewById<Button>(R.id.button2)
        button.backgroundTintList = null

        button.setOnClickListener {
            val intent = Intent(this, sms_password_recovery::class.java)
            startActivity(intent)
        }
    }
}