package com.edumate.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edumate.R
import com.edumate.utils.PreferencesHelper

@Composable
fun RegisterScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Fungsi untuk menangani pendaftaran
    fun onRegister() {
        if (email.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
            // Simpan data pengguna jika validasi berhasil
            preferencesHelper.setEmail(email)
            preferencesHelper.setPassword(password)

            // Pendaftaran berhasil, arahkan ke halaman login
            navController.navigate("login") {
                // Pilih popUpTo untuk menutup halaman pendaftaran
                popUpTo("register") { inclusive = true }
            }
        } else {
            errorMessage = "Pastikan semua kolom diisi dengan benar dan password sesuai."
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Daftar", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Konfirmasi Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan pesan kesalahan jika ada
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onRegister() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk kembali ke login
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Sudah punya akun? Masuk di sini")
        }
    }
}
