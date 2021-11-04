package com.delirium.words.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delirium.words.model.Word
import com.delirium.words.model.WordTest

@Database(
    entities = [ Word::class ],
    version = 1
)
@TypeConverters(WordTypeConverters::class)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}