package com.delirium.words.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.delirium.words.R
import com.delirium.words.database.DBViewModel
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import java.util.*

class StudyWord : AppCompatActivity() {
    private lateinit var wordOrigin: TextView
    private lateinit var wordTranslate: TextView

    private val wordListViewModel: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }

    private val countWord = 5
    private lateinit var currentOriginWord: OriginUserWord
    private var serialNumber = 0
    private var translateWords: MutableList<MeaningWord> = mutableListOf()
    private var usersVersion: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_word)
        Log.i("WORD_APPLICATION", "Start lessonsPage")

        wordOrigin = findViewById(R.id.word_origin)
        wordTranslate = findViewById(R.id.word_translate)

        if (savedInstanceState != null && savedInstanceState.containsKey("ID_SAVE")
            && savedInstanceState.containsKey("ORIGIN_SAVE")
            && savedInstanceState.containsKey("PROGRESS_SAVE")) {
            currentOriginWord = OriginUserWord(
                id = UUID.fromString(savedInstanceState.getString("ID_SAVE")),
                origin = savedInstanceState.getString("ORIGIN_SAVE") ?: "",
                progress = savedInstanceState.getDouble("PROGRESS_SAVE")
            )
            wordOrigin.text = currentOriginWord.origin
            wordTranslate.text = usersVersion
            serialNumber = savedInstanceState.getInt("SERIAL_NUMBER")
        } else {
            getNewWord()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("ID_SAVE", currentOriginWord.id.toString())
        outState.putString("ORIGIN_SAVE", currentOriginWord.origin)
        outState.putString("TRANSLATE_SAVE", usersVersion)
        outState.putDouble("PROGRESS_SAVE", currentOriginWord.progress)
        outState.putInt("SERIAL_NUMBER", serialNumber)
        super.onSaveInstanceState(outState)
    }

    fun nextWord(view: View) {
        Log.i("WORD_APPLICATION", "Click nextWord Button")
        getNewWord()
    }

    fun checkAnswer(view: View) {
        wordTranslate = findViewById(R.id.word_translate)
        usersVersion = wordTranslate.text.toString()
        wordOrigin = findViewById(R.id.word_origin)
        if (usersVersion!!.isNotEmpty()) {
            if (!checkTranslateWord(wordOrigin.text.toString(), usersVersion!!)) {
                if (currentOriginWord.progress > 0.0) currentOriginWord.progress -= 0.1
                wordListViewModel.update(currentOriginWord)
                val toast = Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                serialNumber++
                currentOriginWord.progress += 0.1
                wordListViewModel.update(currentOriginWord)
                val toast = Toast.makeText(this, "Correct", Toast.LENGTH_SHORT)
                toast.show()
                if (serialNumber > countWord) setContentView(R.layout.end_lesson)
            }
        }
    }

    private fun checkTranslateWord(origin: String, answer: String) : Boolean {
        for (curr in translateWords) {
            if (curr.translate == answer && curr.origin == origin) return true
        }
        return false
    }

    fun closePage(view: View) {
        finish()
    }

    private fun getTranslate(word: OriginUserWord) {
        translateWords = mutableListOf()
        val observer = Observer<List<MeaningWord>> { translate ->
            translate?.forEach {
                Log.i("WORD_APPLICATION", "Translate: ${it.translate}, Origin: ${it.origin}")
                translateWords.add(it)
            }
        }
        wordListViewModel.wordTranslation(word.origin).observe(
            this,
            observer
        )
    }

    private fun getNewWord() {
        if (countWord == serialNumber) {
            setContentView(R.layout.end_lesson)
        } else {
            wordListViewModel.userWordListLiveData.observe(
                this,
                Observer { words ->
                    words?.let {
                        do {
                            currentOriginWord = words.random()
                        } while (currentOriginWord.progress > 1)
                        wordOrigin.text = currentOriginWord.origin
                        wordTranslate.text = null
                        serialNumber++
                        getTranslate(currentOriginWord)
                    }
                }
            )
        }
    }
}
