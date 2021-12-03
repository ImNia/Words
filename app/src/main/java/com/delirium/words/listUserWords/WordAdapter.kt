package com.delirium.words.listUserWords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delirium.words.databinding.FragmentNewWordsBinding
import com.delirium.words.model.OriginUserWord
import java.util.*

class WordAdapter(val clickListener: WordListener)
    : ListAdapter<OriginUserWord, WordAdapter.WordHolder>(WordDiffCallback()) {

    class WordHolder(val binding: FragmentNewWordsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OriginUserWord, clickListener: WordListener) {
            binding.wordVariable = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): WordHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentNewWordsBinding.inflate(layoutInflater, parent, false)
                return WordHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = getItem(position)
        holder.bind(word!!, clickListener)
    }
}

class WordDiffCallback : DiffUtil.ItemCallback<OriginUserWord>() {

    override fun areItemsTheSame(oldItem: OriginUserWord, newItem: OriginUserWord): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: OriginUserWord, newItem: OriginUserWord): Boolean {
        return oldItem == newItem
    }
}

class WordListener(val clickListener: (id: UUID) -> Unit) {
    fun onClick(originUserWord: OriginUserWord) {
        clickListener(originUserWord.id)
    }
}