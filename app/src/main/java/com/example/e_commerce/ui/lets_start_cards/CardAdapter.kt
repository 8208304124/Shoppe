package com.example.e_commerce.ui.lets_start_cards

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.ui.letsStarted.MainActivity

class CardAdapter(
    private val context: Context,
    private val cards: List<CardData>
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.textView13)
        val descriptionText: TextView = itemView.findViewById(R.id.textView14)
        val imageView: ImageView = itemView.findViewById(R.id.imageView12)
        val letsStartButton: Button = itemView.findViewById(R.id.lets_start_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.titleText.text = card.title
        holder.descriptionText.text = card.description
        holder.imageView.setImageResource(card.imageRes)
        holder.letsStartButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        // Hide the button for all cards except the last one
        if (position == cards.size - 1) {
            holder.letsStartButton.visibility = View.VISIBLE
        } else {
            holder.letsStartButton.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = cards.size
}

