package com.example.e_commerce.ui.createAccount

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e_commerce.R

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)
        var isPasswordVisible = false
        val passwordField = findViewById<EditText>(R.id.passwordText)
        val toggleIcon = findViewById<ImageView>(R.id.toggleIcon)
        val button = findViewById<Button>(R.id.button2)
        button.backgroundTintList = null
        toggleIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                passwordField.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                toggleIcon.setImageResource(R.drawable.eye_open) // icon for hiding
            } else {
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                toggleIcon.setImageResource(R.drawable.eye_close) // icon for showing
            }
            passwordField.setSelection(passwordField.text.length)
        }
    }
}