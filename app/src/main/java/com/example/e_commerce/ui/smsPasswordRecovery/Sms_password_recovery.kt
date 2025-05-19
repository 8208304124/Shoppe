package com.example.e_commerce.ui.smsPasswordRecovery

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R
import com.example.e_commerce.ui.setup_new_password.setup_new_password

class sms_password_recovery : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var dot1: TextView
    private lateinit var dot2: TextView
    private lateinit var dot3: TextView
    private lateinit var dot4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_password_recovery)
        val button = findViewById<Button>(R.id.button2)
        val cancelBtn = findViewById<TextView>(R.id.button3)
        button.backgroundTintList = null

        editText = findViewById(R.id.otp_input)
        dot1 = findViewById(R.id.dot1)
        dot2 = findViewById(R.id.dot2)
        dot3 = findViewById(R.id.dot3)
        dot4 = findViewById(R.id.dot4)

        val dots = listOf(dot1, dot2, dot3, dot4)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val length = s?.length ?: 0
                for (i in 0..3) {
                    dots[i].setBackgroundResource(
                        if (i < length) R.drawable.otp_dot else R.drawable.otp_dot
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        button.setOnClickListener {
            val intent = Intent(this, setup_new_password::class.java)
            startActivity(intent)
        }

        cancelBtn.setOnClickListener{
            finish()
        }

    }
}
