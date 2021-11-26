package com.delirium.words.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.Word
import java.util.*

class WordDescription(val id: UUID) : Fragment() {
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
        progressDesc = view.findViewById(R.id.editTextNumberDecimal)

        val wordDatabase = ViewModelProvider(this).get(DBViewModel::class.java)
        wordDatabase.wordLiveData(id).observe (
            viewLifecycleOwner,
            Observer { word ->
                if (word != null) {
                    updateData(word)
                }
            }
        )
        return view
    }

    private fun updateData(word: Word) {
        originDesc.text = word.origin
        translateDesc.text = word.translate
        progressDesc.text = word.progress.toString()
    }

    companion object {
        fun newInstance(id: UUID) : WordDescription {
            return WordDescription(id)
        }
    }
}