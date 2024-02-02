package com.tatara.fiszki.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatara.fiszki.Destinations
import com.tatara.fiszki.R
import com.tatara.fiszki.repository.Repository
import com.tatara.fiszki.room.FlashcardsDB
import com.tatara.fiszki.ui.theme.FiszkiTheme
import com.tatara.fiszki.viewmodel.FlashcardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiszkiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // navigation
                    val ctx = LocalContext.current.applicationContext
                    val db = FlashcardsDB.getInstance(ctx)
                    val repo = Repository(db)
                    val myModel = FlashcardViewModel(repo)

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.HomeScreen.toString()
                    ) {
                        composable(route = Destinations.HomeScreen.toString()) {
                            HomeScreen(navController)
                        }
                        composable(route = Destinations.FlashcardScreen.toString()) {
                            FlashcardScreen(myModel, navController)
                        }
                        composable(route = Destinations.FlashcardsListScreen.toString()) {
                            FlashcardsListScreen(myModel)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Wybierz język",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp), // Add horizontal padding between images
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.wielka_brytania),
                contentDescription = "angielski",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Destinations.FlashcardScreen.toString())
                    }
            )
            Image(
                painter = painterResource(R.drawable.niemcy),
                contentDescription = "niemiecki",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
        }
    }
}

//@Preview(showBackground = true, device = "id:pixel_7", showSystemUi = false)
//@Composable
//fun HomeScreenPreview() {
//    FiszkiTheme {
//        HomeScreen()
//    }
//}