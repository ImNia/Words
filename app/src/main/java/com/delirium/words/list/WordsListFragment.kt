package com.delirium.words.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.databinding.FragmentWordsListBinding

class WordsListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentWordsListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_words_list, container, false)

        val wordListViewModel =
            ViewModelProvider(this).get(DBViewModel::class.java)

        binding.wordListView = wordListViewModel

        val adapter = WordAdapter(WordListener { id ->
            val fragment = WordDescription.newInstance(id)
            activity?.let{
                it.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
        binding.fragmentWordsList.adapter = adapter

        wordListViewModel.wordListLiveData.observe(
            viewLifecycleOwner,
            Observer { words ->
                words?.let {
                    Log.i("WORD_LIST_FRAGMENT", "Got word:${words.size}")
                    adapter.submitList(words)
                }
            }
        )
        binding.setLifecycleOwner(this)

        val manager = LinearLayoutManager(activity)
        binding.fragmentWordsList.layoutManager = manager

        return binding.root
    }

    companion object {
        fun newInstant(): WordsListFragment {
            return WordsListFragment()
        }
    }
}