package com.example.e_commerce.ui.lets_start_cards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerce.R
import com.example.e_commerce.ui.letsStarted.MainActivity

class lets_start_cards : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_start_cards)
        val button = findViewById<EditText>(R.id.dot1)

        button.backgroundTintList = null

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dots_layout)



        val cards = listOf(
            CardData("Hello...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus.", R.drawable.girlshoppingbag1),
            CardData("Set...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", R.drawable.placeholder_01),
            CardData("Goo...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus.", R.drawable.girlshoppingbag1),
            CardData("Ready?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", R.drawable.placeholder_01)
        )

        viewPager.adapter = CardAdapter(this, cards)

        setupDots(cards.size)
        highlightDot(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                highlightDot(position)
            }
        })
    }


    private fun setupDots(count: Int) {
        dots = arrayOfNulls(count)
        dotsLayout.removeAllViews()

        for (i in 0 until count) {
            dots[i] = ImageView(this).apply {
                setImageResource(R.drawable.radio_unchecked)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(8, 0, 8, 0)
                layoutParams = params
            }
            dotsLayout.addView(dots[i])
        }
    }

    private fun highlightDot(position: Int) {
        for (i in dots.indices) {
            dots[i]?.setImageResource(
                if (i == position) R.drawable.radio_checked else R.drawable.radio_unchecked
            )
        }
    }
}
