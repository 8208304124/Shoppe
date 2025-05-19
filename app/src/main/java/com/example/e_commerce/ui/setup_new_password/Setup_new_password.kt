package com.example.e_commerce.ui.setup_new_password

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R
import com.example.e_commerce.ui.lets_start_cards.lets_start_cards

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