package com.delirium.words.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.delirium.words.R

class WordDescription : Fragment() {
    private lateinit var originDesc: TextView
    private lateinit var translateDesc: TextView
    private lateinit var progressDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TAG", "Create WordDescription")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.word_description, container, false)
        originDesc = view.findViewById(R.id.description_word_origin)
        translateDesc = view.findViewById(R.id.description_word_translate)
        progressDesc = view.findViewById(R.id.description_word_progress)

        return view
    }

    companion object {
        fun newInstance() : WordDescription {
            return WordDescription()
        }
    }
}