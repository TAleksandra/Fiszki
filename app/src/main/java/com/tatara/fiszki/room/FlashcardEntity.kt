package com.tatara.fiszki.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlashcardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val wordENGorDE: String,
    val wordPL: String,
    var progress: Float
)
