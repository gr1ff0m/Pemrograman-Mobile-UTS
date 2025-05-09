package com.edumate.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edumate.utils.PreferencesHelper

@Composable
fun ProfileScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    // Ambil email dari SharedPreferences
    val userEmail = preferencesHelper.getEmail() ?: "No email found"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Kembali"
            )
        }

        Text(
            text = "Profile Screen",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tampilkan email pengguna
        Text(
            text = "Email: $userEmail",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                preferencesHelper.clearUserData()
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true } // Menghapus history agar tidak bisa kembali
                }
            },
            modifier = Modifier.fillMaxWidth()
        )  {
            Text("Logout")
        }
    }
}
