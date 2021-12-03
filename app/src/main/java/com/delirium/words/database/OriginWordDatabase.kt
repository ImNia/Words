package com.delirium.words.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delirium.words.model.MeaningWord
import com.delirium.words.model.OriginUserWord
import com.delirium.words.model.OriginWord

@Database(
    entities = [ OriginWord::class, MeaningWord::class, OriginUserWord::class ],
    version = 1
)
@TypeConverters(WordTypeConverters::class)
abstract class OriginWordDatabase : RoomDatabase() {
    abstract fun wordDao(): OriginWordDao
}