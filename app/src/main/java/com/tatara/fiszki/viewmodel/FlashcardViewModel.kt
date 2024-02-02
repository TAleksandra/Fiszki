package com.tatara.fiszki.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatara.fiszki.repository.Repository
import com.tatara.fiszki.room.FlashcardEntity
import kotlinx.coroutines.launch

class FlashcardViewModel(val repository: Repository): ViewModel() {

    var answearVisibility by  mutableFloatStateOf(0f)

    fun showAnswear(){
        answearVisibility = 1f
    }

    fun hideAnswear() {
        answearVisibility = 0f
    }

    fun addFlashcard(flashcard: FlashcardEntity) {
        viewModelScope.launch {
            repository.insertFlashcardToRoom(flashcard)
        }
    }

    val getAllFlashcards = repository.getAllFlashcards()

    fun deleteFlashcard(flashcard: FlashcardEntity) {
        viewModelScope.launch {
            repository.deleteFlashcardFromRoom(flashcard)
        }
    }

    fun updateFlashcard(flashcard: FlashcardEntity) {
        viewModelScope.launch {
            repository.updateFlashcardInRoom(flashcard)
        }
    }

    val getRandomFlashcard = repository.getRandomFlashcardFromRoom()

    fun addProgress(flashcard: FlashcardEntity?) {
        viewModelScope.launch {
            hideAnswear()

            if (flashcard  != null) {
                if (flashcard.progress < 1.0) {
                    val updatedProgress = (flashcard .progress + 0.05f).coerceIn(0f, 1f)
                    flashcard.progress = updatedProgress
                } else deleteFlashcard(flashcard)

                updateFlashcard(flashcard)
            }
        }
    }

    fun substractProgress(flashcard: FlashcardEntity?) {
        viewModelScope.launch {
            hideAnswear()

            if (flashcard != null) {
                if (flashcard.progress > 0.0) {
                    val updatedProgress = (flashcard.progress - 0.1f).coerceIn(0f, 1f)
                    flashcard.progress = updatedProgress
                }

                updateFlashcard(flashcard)
            }
        }
    }

}
