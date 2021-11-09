package com.delirium.words.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delirium.words.R
import com.delirium.words.model.Word

class WordAdapter(private val words: List<Word>)
    : RecyclerView.Adapter<WordAdapter.WordHolder>() {

    class WordHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originTextView: TextView = itemView.findViewById(R.id.wordPageOrigin)
        val translateTextView: TextView = itemView.findViewById(R.id.wordPageTranslate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_words, parent, false)
        return WordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = words[position]
        holder.apply {
            originTextView.text = word.origin
            translateTextView.text = word.translate
        }
    }

    override fun getItemCount() = words.size
}