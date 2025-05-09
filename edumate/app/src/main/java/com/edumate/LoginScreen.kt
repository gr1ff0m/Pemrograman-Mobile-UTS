package com.edumate.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edumate.utils.PreferencesHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Login") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("home") }, modifier = Modifier.fillMaxWidth()) {
                Text("Login")
            }
        }
    }
}
