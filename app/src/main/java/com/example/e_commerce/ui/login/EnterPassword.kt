package com.example.e_commerce.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.ui.bottomTabs.BottomNavTabs
import com.example.e_commerce.R
import com.example.e_commerce.ui.passwordrecovery.password_recovery

class EnterPassword : AppCompatActivity() {

    private lateinit var toggleIcon: ImageView
    private lateinit var button: Button
    private lateinit var passwordField: EditText
    private lateinit var forgotText: TextView
    private lateinit var notYouBtn: TextView
    private lateinit var notYouBtn2: ImageView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_password)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        toggleIcon = findViewById(R.id.toggleIcon)
        button = findViewById(R.id.button2)
        passwordField = findViewById(R.id.passwordText)
        forgotText = findViewById(R.id.textView9)
        notYouBtn = findViewById(R.id.textView4)
        notYouBtn2 = findViewById(R.id.imageView8)

        button.backgroundTintList = null
    }

    private fun setupListeners() {
        forgotText.setOnClickListener {
            startActivity(Intent(this, password_recovery::class.java))
        }

        val notYouClickListener = View.OnClickListener { finish() }
        notYouBtn.setOnClickListener(notYouClickListener)
        notYouBtn2.setOnClickListener(notYouClickListener)
        button.setOnClickListener {
            val intent = Intent(this, BottomNavTabs::class.java)
            startActivity(intent)
        }
        toggleIcon.setOnClickListener { togglePasswordVisibility() }
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        passwordField.inputType = if (isPasswordVisible) {
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        toggleIcon.setImageResource(
            if (isPasswordVisible) R.drawable.eye_open else R.drawable.eye_close
        )

        passwordField.setSelection(passwordField.text.length)
    }
}
