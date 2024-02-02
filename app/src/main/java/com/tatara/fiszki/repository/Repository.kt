package com.tatara.fiszki.repository

import com.tatara.fiszki.room.FlashcardEntity
import com.tatara.fiszki.room.FlashcardsDB

class Repository(val flashcardsDB: FlashcardsDB) {

    suspend fun insertFlashcardToRoom(flashcardEntity: FlashcardEntity) {
        flashcardsDB.flashcardDao().insertFlashcard(flashcardEntity)
    }

    fun getAllFlashcards() = flashcardsDB.flashcardDao().getAllFlashcards()

    suspend fun deleteFlashcardFromRoom(flashcardEntity: FlashcardEntity) {
        flashcardsDB.flashcardDao().deleteFlashcard(flashcardEntity)
    }

    suspend fun updateFlashcardInRoom(flashcardEntity: FlashcardEntity) {
        flashcardsDB.flashcardDao().updateFlashcard(flashcardEntity)
    }

    fun getRandomFlashcardFromRoom() = flashcardsDB.flashcardDao().getRandomFlashcard()

}