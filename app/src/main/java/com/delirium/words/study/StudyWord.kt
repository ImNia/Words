package com.delirium.words.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.Word
import java.util.*

class StudyWord : AppCompatActivity() {
    lateinit var wordOrigin: TextView
    lateinit var wordTranslate: TextView

    private val wordListViewModel: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }

    private lateinit var currentWord: Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_word)
        Log.i("LESSONSPAGE", "Start lessonsPage")

        wordOrigin = findViewById(R.id.word_origin)
        wordTranslate = findViewById(R.id.word_translate)

        if (savedInstanceState != null && savedInstanceState.containsKey("ID_SAVE")
            && savedInstanceState.containsKey("ORIGIN_SAVE")
            && savedInstanceState.containsKey("TRANSLATE_SAVE")
            && savedInstanceState.containsKey("PROGRESS_SAVE")) {
            currentWord = Word(
                id = UUID.fromString(savedInstanceState.getString("ID_SAVE")),
                origin = savedInstanceState.getString("ORIGIN_SAVE") ?: "",
                translate = savedInstanceState.getString("TRANSLATE_SAVE") ?: "",
                progress = savedInstanceState.getDouble("PROGRESS_SAVE")
            )
            wordOrigin.text = currentWord.origin
            wordTranslate.text = currentWord.translate
        } else {
            getNewWord()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("ID_SAVE", currentWord.id.toString())
        outState.putString("ORIGIN_SAVE", currentWord.origin)
        outState.putString("TRANSLATE_SAVE", currentWord.translate)
        outState.putDouble("PROGRESS_SAVE", currentWord.progress)
        super.onSaveInstanceState(outState)
    }

    fun nextWord(view: View) {
        Log.i("BUTTON", "Click nextWord Button")
        getNewWord()
    }

    fun checkAnswer(view: View) {
        wordTranslate = findViewById(R.id.word_translate)
        if (wordTranslate.text.toString() != currentWord.translate) {
            val toast = Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            currentWord.progress += 0.1
            wordListViewModel.update(currentWord)
            val toast = Toast.makeText(this, "Correct", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun getNewWord() {
        wordListViewModel.wordListLiveData.observe(
            this,
            Observer { words ->
                words?.let {
                    currentWord = words.random()
                    Log.i("LESSONSPAGE", "${currentWord.id} : ${currentWord.origin} / ${currentWord.translate} :: ${currentWord.progress}")
                    wordOrigin.text = currentWord.origin
                    wordTranslate.text = null
                }
            }
        )
    }
}
