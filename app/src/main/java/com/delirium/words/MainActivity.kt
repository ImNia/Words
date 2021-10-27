package com.delirium.words

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startStudy(view: View) {
        Log.i("TEST", "Press button Study")
    }

    fun viewWords(view: View) {
        Log.i("TEST", "Press button Words")
    }

    fun newWord(view: View) {
        Log.i("TEST", "Press button New  Word")
    }
}