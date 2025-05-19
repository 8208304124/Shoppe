package com.example.e_commerce.ui.smsPasswordRecovery

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.e_commerce.R
import com.example.e_commerce.ui.setup_new_password.setup_new_password

class Sms_password_recovery : AppCompatActivity() {
    private lateinit var editTexts: List<EditText>

    private fun navigateToSetupPassword() {
        val intent = Intent(this, setup_new_password::class.java)
        startActivity(intent)
    }
    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_password_recovery)
        val editTexts = listOf<EditText>(
            findViewById(R.id.dot1),
            findViewById(R.id.dot2),
            findViewById(R.id.dot3),
            findViewById(R.id.dot4)
        )
        editTexts[0].requestFocus()
        val button = findViewById<Button>(R.id.button2)
        val cancelBtn = findViewById<TextView>(R.id.button3)


        button.backgroundTintList = null
        for (i in editTexts.indices) {
            val currentEditText = editTexts[i]

            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        currentEditText.background = ContextCompat.getDrawable(this@Sms_password_recovery, R.drawable.otp_dot_filled)
                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        } else {
                            val otpCode = editTexts.joinToString("") { it.text.toString() }
                            if (otpCode.length == 4) {
                                hideKeyboard()
                                navigateToSetupPassword()
                            }
                        }
                    } else {
                        currentEditText.background = ContextCompat.getDrawable(this@Sms_password_recovery, R.drawable.otp_dot_filled)
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            currentEditText.setOnKeyListener { _, keyCode, event ->
                if (event.action == android.view.KeyEvent.ACTION_DOWN && keyCode == android.view.KeyEvent.KEYCODE_DEL) {
                    if (currentEditText.text.isNotEmpty()) {
                        // First press: clear current field
                        currentEditText.setText("")
                        currentEditText.background = ContextCompat.getDrawable(this@Sms_password_recovery, R.drawable.otp_dot)
                        return@setOnKeyListener true
                    } else if (i > 0) {
                        // Second press: move to previous
                        editTexts[i - 1].requestFocus()
                        editTexts[i - 1].setText("")
                        editTexts[i - 1].background = ContextCompat.getDrawable(this@Sms_password_recovery, R.drawable.otp_dot)
                        return@setOnKeyListener true
                    }
                }
                false
            }

        }

        cancelBtn.setOnClickListener{
            finish()
        }

    }
}
