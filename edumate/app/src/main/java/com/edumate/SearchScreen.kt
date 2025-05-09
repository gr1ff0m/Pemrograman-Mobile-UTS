package com.edumate.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.edumate.R
import com.edumate.utils.PreferencesHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    var query by remember { mutableStateOf("") }
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.search)) }) }, // Ganti hardcoded dengan stringResource
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text(stringResource(id = R.string.search_hint)) }, // Ganti hardcoded dengan stringResource
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        }
    }
}
