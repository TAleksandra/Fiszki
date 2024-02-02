package com.tatara.fiszki.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatara.fiszki.room.FlashcardEntity
import com.tatara.fiszki.viewmodel.FlashcardViewModel


@Composable
fun FlashcardsListScreen(model: FlashcardViewModel) {
    val flashcards by model.getAllFlashcards.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier.background(Color.DarkGray)
    ) {
        AddingFlashcard(model)
        Spacer(modifier = Modifier.height(20.dp))
        if (flashcards.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.background(Color.DarkGray)
            ) {
                items(flashcards) {
                    FlashcardListItem(model = model, flashcard = it)
                }
            }
        } else {
            Text(
                text = "Brak fiszek w bazie",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp),
                color = Color.LightGray
            )
        }
    }

}

@Composable
fun AddingFlashcard(model: FlashcardViewModel) {
    var wordPL by remember { mutableStateOf("") }
    var wordENGDE by remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .padding(8.dp, top = 12.dp)
            .fillMaxWidth()
    ) {
        Column {
            OutlinedTextField(
                value = wordPL,
                label = { Text(text = "PL") },
                placeholder = { Text(text = "słówko po polsku") },
                onValueChange = { wordPL = it },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = wordENGDE,
                label = { Text(text = "ANG") },
                placeholder = { Text(text = "słówko po angielsku") },
                onValueChange = { wordENGDE = it },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    val newFlashcard = FlashcardEntity(0, wordENGDE, wordPL, 0f)
                    if (wordENGDE != "" && wordPL != "")
                        model.addFlashcard(newFlashcard)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Dodaj")
            }
        }
    }
}

@Composable
fun FlashcardListItem(model: FlashcardViewModel, flashcard: FlashcardEntity) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "\uD83C\uDDF5\uD83C\uDDF1 ${flashcard.wordPL}",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            )
            Text(
                text = "\uD83C\uDDEC\uD83C\uDDE7 ${flashcard.wordENGorDE}",
                fontSize = 24.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            )
            IconButton(onClick = { model.deleteFlashcard(flashcard) }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
    }
}