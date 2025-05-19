package com.example.e_commerce.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
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
        val notYouBtn = findViewById<TextView>(R.id.textView4)
        val notYouBtn2 = findViewById<ImageView>(R.id.imageView8)

        forgotText.setOnClickListener {
            val intent = Intent(this, password_recovery::class.java)
            startActivity(intent)
        }
        val notYouClickListener = View.OnClickListener {
            finish()
        }
        notYouBtn.setOnClickListener(notYouClickListener)
        notYouBtn2.setOnClickListener(notYouClickListener)

    }
}