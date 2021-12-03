package com.delirium.words.listUserWords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delirium.words.R

class UserWordViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_new_words)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_new)

        if (currentFragment == null) {
            val fragment = UserWordsListFragment.newInstant()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_new, fragment)
                .commit()
        }
    }
}