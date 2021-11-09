package com.delirium.words.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.Word

class WordsListFragment : Fragment() {
    private lateinit var recycleView: RecyclerView
    private var adapter: WordAdapter? = WordAdapter(emptyList())

    private val wordListViewModel: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_words_list, container, false)
        recycleView = view.findViewById(R.id.fragment_words_list) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordListViewModel.wordListLiveData.observe(
            viewLifecycleOwner,
            Observer { words ->
                words?.let {
                    Log.i("WORD_LIST_FRAGMENT", "Got word:${words.size}")
                    updateUI(it)
                }
            }
        )
    }

    private fun updateUI(words: List<Word>) {
        adapter = WordAdapter(words)
        recycleView.adapter = adapter
    }

    companion object {
        fun newInstant(): WordsListFragment {
            return WordsListFragment()
        }
    }
}