package com.delirium.words.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "origin_word")
data class OriginWord(
    @PrimaryKey
    val id: UUID,
    val origin: String,
    var progress: Double
)