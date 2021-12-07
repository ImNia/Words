package com.delirium.words

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.delirium.words.listNewWords.WordViewActivity
import com.delirium.words.listUserWords.UserWordViewActivity
import com.delirium.words.study.StudyWord

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startStudy(view: View) {
        val studyPage = Intent(this, StudyWord::class.java)
        startActivity(studyPage)
    }

    fun viewWords(view: View) {
        val viewWordsPage = Intent(this, UserWordViewActivity::class.java)
        startActivity(viewWordsPage)
    }

    fun newWord(view: View) {
        val viewNewWordsPage = Intent(this, WordViewActivity::class.java)
        startActivity(viewNewWordsPage)
    }
}