package com.tatara.fiszki.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tatara.fiszki.Destinations
import com.tatara.fiszki.room.FlashcardEntity
import com.tatara.fiszki.viewmodel.FlashcardViewModel

@Composable
fun FlashcardScreen(model: FlashcardViewModel, navController: NavController) {
    val randomFlashcard by model.getRandomFlashcard.collectAsState(initial = null)
    Log.e("TAGY", "Losowa: $randomFlashcard")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate(Destinations.FlashcardsListScreen.toString()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(text = "Lista słówek")
        }
//        Spacer(modifier = Modifier.height(30.dp))
        Fiszka(randomFlashcard, model) {
            model.addProgress(randomFlashcard)
        }
//        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    model.substractProgress(randomFlashcard)
                },
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier.size(width = 150.dp, height = 50.dp),
                elevation = ButtonDefaults.buttonElevation(10.dp)
            ) {
                Text(
                    text = "Nie wiedziałem",
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Button(
                onClick = {
                    model.addProgress(randomFlashcard)
                },
                colors = ButtonDefaults.buttonColors(Color.Green),
                modifier = Modifier.size(width = 150.dp, height = 50.dp),
                elevation = ButtonDefaults.buttonElevation(10.dp)
            ) {
                Text(
                    text = "Wiedziałem",
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fiszka(randomFlashcard: FlashcardEntity?, model: FlashcardViewModel, onProgressChange: (FlashcardEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight(),
        onClick = {
            model.showAnswear()
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LinearProgressIndicator(
                progress = randomFlashcard?.progress ?: 0f,
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Text(
                text = randomFlashcard?.wordPL ?: "Brak fiszek",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(),
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = randomFlashcard?.wordENGorDE ?: "",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4CAF50),
                modifier = Modifier
                    .alpha(model.answearVisibility)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Kliknij, żeby sprawdzić tłumaczenie",
                fontSize = 12.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(bottom = 22.dp)
            )
        }
    }
}
