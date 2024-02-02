package com.tatara.fiszki

sealed class Destinations(val route: String) {
    object HomeScreen: Destinations("HomeScreen")
    object FlashcardScreen: Destinations("FlashcardScreen")
    object FlashcardsListScreen: Destinations("FlashcardsListScreen")
}