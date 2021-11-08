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
import com.delirium.words.model.Word

class StudyWord : AppCompatActivity() {
    lateinit var wordOrigin: TextView
    lateinit var wordTranslate: TextView

    private val wordListViewModel: LessonViewModel by lazy {
        ViewModelProvider(this).get(LessonViewModel::class.java)
    }

    private lateinit var currentWord: Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_word)
        Log.i("LESSONSPAGE", "Start lessonsPage")

        wordOrigin = findViewById(R.id.word_origin)
        wordTranslate = findViewById(R.id.word_translate)

        getNewWord()
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
