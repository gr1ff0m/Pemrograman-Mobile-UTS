package com.edumate.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.edumate.R
import com.edumate.utils.PreferencesHelper
import com.edumate.utils.exitApp

@Composable
fun HomeScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Menggunakan stringResource untuk mendapatkan teks dari R.string
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Navigasi ke Flashcards
        Button(
            onClick = { navController.navigate("flashcards") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.flashcard_button))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigasi ke Pomodoro
        Button(
            onClick = { navController.navigate("pomodoro") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.pomodoro_button))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigasi ke Task Manager
        Button(
            onClick = { navController.navigate("tasks") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.tasks_button))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Baris tombol ke Profile dan Settings
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { navController.navigate("profile") }
            ) {
                Text(text = stringResource(id = R.string.profile_button))
            }

            OutlinedButton(
                onClick = { navController.navigate("settings") }
            ) {
                Text(text = stringResource(id = R.string.settings_button))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol untuk keluar aplikasi
        Button(
            onClick = {
                preferencesHelper.resetRememberMe()
                exitApp(context) // Oper context ke fungsi exitApp
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.logout_button)) // Tombol Keluar
        }
    }
}
