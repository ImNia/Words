package com.delirium.words.listUserWords

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import java.util.*

class UserWordDescription(val id: UUID) : Fragment() {
    private lateinit var originDesc: TextView
    private lateinit var translateDesc: TextView
    private lateinit var progressDesc: TextView

    private val wordDatabase: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("WORD_APPLICATION", "Create WordDescription")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.user_word_description, container, false)
        originDesc = view.findViewById(R.id.description_user_word_origin)
        translateDesc = view.findViewById(R.id.description_user_word_translate)
        progressDesc = view.findViewById(R.id.progress_user_word)

        wordDatabase.userWordLiveData(id).observe (
            viewLifecycleOwner,
            Observer { word ->
                word?.let {
                    getTranslate(word)
                    Log.i("WORD_APPLICATION", "Word: ${word.id} :: ${word.origin}:: ${word.progress}")
                }
            }
        )

        return view
    }

    private fun updateData(word: OriginUserWord, translateWord: MeaningWord) {
        val progress = if ( word.progress > 1 ) 100 else word.progress * 100
        originDesc.text = word.origin
        translateDesc.text = translateWord.translate
        progressDesc.text = progress.toInt().toString() + "%"
    }

    private fun getTranslate(word: OriginUserWord) {
        wordDatabase.wordTranslation(word.origin).observe(
            viewLifecycleOwner,
            Observer { translate ->
                updateData(word, translate.first())
            }
        )

    }

    companion object {
        fun newInstance(id: UUID) : UserWordDescription {
            return UserWordDescription(id)
        }
    }
}