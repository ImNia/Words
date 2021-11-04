package com.delirium.words

import android.app.Application

class WordIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        WordRepository.initialize(this)
    }
}