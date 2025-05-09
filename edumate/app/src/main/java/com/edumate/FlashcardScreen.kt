package com.edumate.ui.flashcards

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.edumate.R

// Data untuk Flashcard
data class Flashcard(val question: String, val answer: String)

@Composable
fun FlashcardsScreen(navController: NavController) {
    val flashcards = listOf(
        Flashcard(
            question = stringResource(R.string.flashcard_1_question),
            answer = stringResource(R.string.flashcard_1_answer)
        ),
        Flashcard(
            question = stringResource(R.string.flashcard_2_question),
            answer = stringResource(R.string.flashcard_2_answer)
        ),
        Flashcard(
            question = stringResource(R.string.flashcard_3_question),
            answer = stringResource(R.string.flashcard_3_answer)
        )
    )

    var currentCardIndex by remember { mutableStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }

    val currentCard = flashcards[currentCardIndex]
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Flashcard ${currentCardIndex + 1} / ${flashcards.size}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = currentCard.question,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        if (showAnswer) {
            Text(
                text = currentCard.answer,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Green,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }

        Button(
            onClick = { showAnswer = !showAnswer },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (showAnswer)
                    stringResource(R.string.hide_answer)
                else
                    stringResource(R.string.show_answer)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentCardIndex > 0) {
                        currentCardIndex -= 1
                        showAnswer = false
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(id = R.string.previous))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (currentCardIndex < flashcards.size - 1) {
                        currentCardIndex += 1
                        showAnswer = false
                    } else {

                        Toast.makeText(
                            context,
                            context.getString(R.string.flashcard_complete_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.next))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.back_to_home))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashcardsScreenPreview() {
    FlashcardsScreen(navController = rememberNavController())
}
