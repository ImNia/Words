package com.delirium.words.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.delirium.words.R
import com.delirium.words.WordRepository
import com.delirium.words.model.Word
import com.delirium.words.model.WordTest
import java.util.*

class StudyWord : AppCompatActivity() {

    lateinit var wordOrigin: TextView
//    private val lesson = Lesson(WordRepository.get())

    private var liveData = MutableLiveData<Word>()
    private val wordListViewModel: Lesson by lazy {
        ViewModelProvider(this).get(Lesson::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_word)
        Log.i("LESSONSPAGE", "Start lessonsPage")

        wordOrigin = findViewById(R.id.word_origin)

        wordListViewModel.wordListLiveData.observe(
            this,
            Observer {
                words ->
                words?.let {
                    Log.i("LESSONPAGE", "${words.size}")
                    wordOrigin.text = it.first().origin
                }
            }
        )
//        liveData.value = lesson.getWord(UUID.fromString("3acc325f-55cb-4711-8b35-4094589ce86b"))

        /*lesson.wordListData.observe(
            lifecycle,

        )*/
        val tmp = liveData.value
        Log.i("LESSONSPAGE", "${tmp}")
    }
}