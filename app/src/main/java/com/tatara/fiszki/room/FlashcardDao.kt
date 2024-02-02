package com.tatara.fiszki.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {

    @Insert
    suspend fun insertFlashcard(flashcardEntity: FlashcardEntity)

    @Query("SELECT * FROM FlashcardEntity")
    fun getAllFlashcards(): Flow<List<FlashcardEntity>>

    @Delete
    suspend fun deleteFlashcard(flashcardEntity: FlashcardEntity)

    @Update
    suspend fun updateFlashcard(flashcardEntity: FlashcardEntity)

    @Query("SELECT * FROM FlashcardEntity ORDER BY RANDOM() LIMIT 1")
    fun getRandomFlashcard(): Flow<FlashcardEntity>
}