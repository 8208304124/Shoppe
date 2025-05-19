package com.example.e_commerce.ui.setup_new_password

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e_commerce.R
import com.example.e_commerce.lets_start_cards
import com.example.e_commerce.ui.smsPasswordRecovery.Sms_password_recovery

class setup_new_password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setup_new_password)
        val button = findViewById<Button>(R.id.button2)
        val cancelBtn = findViewById<TextView>(R.id.button3)

        button.backgroundTintList = null
        button.setOnClickListener {
            val intent = Intent(this, lets_start_cards::class.java)
            startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            finish()
        }
    }
}