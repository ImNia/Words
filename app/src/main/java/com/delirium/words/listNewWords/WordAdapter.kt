package com.delirium.words.listNewWords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delirium.words.databinding.FragmentWordsBinding
import com.delirium.words.model.OriginWord
import java.util.*

class WordAdapter(val clickListener: WordListener)
    : ListAdapter<OriginWord, WordAdapter.WordHolder>(WordDiffCallback()) {

    class WordHolder(val binding: FragmentWordsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OriginWord, clickListener: WordListener) {
            binding.wordVariable = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): WordHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentWordsBinding.inflate(layoutInflater, parent, false)
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

class WordDiffCallback : DiffUtil.ItemCallback<OriginWord>() {

    override fun areItemsTheSame(oldItem: OriginWord, newItem: OriginWord): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: OriginWord, newItem: OriginWord): Boolean {
        return oldItem == newItem
    }
}

class WordListener(val clickListener: (id: UUID) -> Unit) {
    fun onClick(originWord: OriginWord) {
        clickListener(originWord.id)
    }
}