package com.delirium.words.listUserWords

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import java.util.*

class CreateNewWord : Fragment() {
    private lateinit var origin: EditText
    private lateinit var translate: EditText
    private lateinit var button: Button

    private val wordViewModel: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("WORD_APPLICATION", "Open create new word page")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("WORD_APPLICATION", "Open onCreateView")
        val view = inflater.inflate(R.layout.create_new_word, container, false)
        origin = view.findViewById(R.id.addNewWord)
        translate = view.findViewById(R.id.addTranslateNewWord)
        button = view.findViewById(R.id.createButton)

        val id = UUID.randomUUID()
        button.setOnClickListener {
            val newWord = OriginUserWord(
                id = id,
                origin = origin.text.toString(),
                progress = 0.0
            )
            val newTranslate = MeaningWord(
                id = id,
                origin = origin.text.toString(),
                translate = translate.text.toString()
            )
            wordViewModel.insertUserWord(newWord)
            wordViewModel.insertMeaningWord(newTranslate)

            Log.i("WORD_APPLICATION", "${newWord.id} :: ${newWord.origin} :: ${newWord.progress}")
            Log.i("WORD_APPLICATION", "${newTranslate.id} :: ${newTranslate.origin} :: ${newTranslate.translate}")
        }

        return view
    }

    companion object {
        fun newInstance() : CreateNewWord {
            return CreateNewWord()
        }
    }
}