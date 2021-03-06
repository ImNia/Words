package com.delirium.words.listNewWords

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import com.delirium.words.model.OriginWord
import java.util.*

class WordDescription(val id: UUID) : Fragment() {
    private lateinit var originDesc: TextView
    private lateinit var translateDesc: TextView
    private lateinit var buttonAddToStudy: Button

    private lateinit var currentWord: OriginWord

    private val wordDatabase: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("WORD_APPLICATION", "Create WordDescription")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.word_description, container, false)
        originDesc = view.findViewById(R.id.description_word_origin)
        translateDesc = view.findViewById(R.id.description_word_translate)
        buttonAddToStudy = view.findViewById(R.id.addToStudy)

        wordDatabase.wordLiveData(id).observe (
            viewLifecycleOwner,
            Observer { word ->
                word?.let {
                    getTranslate(word)
                    Log.i("WORD_APPLICATION", "Word: ${word.id} :: ${word.origin}")
                }
            }
        )

        buttonAddToStudy.setOnClickListener {
            newWord()
        }

        return view
    }

    private fun updateData(word: OriginWord, translateWord: MeaningWord) {
        originDesc.text = word.origin
        translateDesc.text = translateWord.translate
        currentWord = word
    }

    private fun getTranslate(word: OriginWord) {
        wordDatabase.wordTranslation(word.origin).observe(
            viewLifecycleOwner,
            Observer { translate ->
                updateData(word, translate.first())
            }
        )

    }

    private fun newWord() {
        Log.i("WORD_APPLICATION", "Click on button to add word")
        val newUserWord = OriginUserWord(
            id = currentWord.id,
            origin = currentWord.origin,
            progress = 0.0
        )
        wordDatabase.insertUserWord(newUserWord)
    }

    companion object {
        fun newInstance(id: UUID) : WordDescription {
            return WordDescription(id)
        }
    }
}