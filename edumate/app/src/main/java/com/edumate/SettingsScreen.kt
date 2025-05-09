package com.edumate.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edumate.R
import com.edumate.utils.PreferencesHelper
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavController,
    preferencesHelper: PreferencesHelper,
    toggleDarkMode: (Boolean) -> Unit,
    darkModeState: Boolean,
    onLanguageChange: (String) -> Unit
) {
    var selectedLanguage by remember { mutableStateOf(preferencesHelper.getSelectedLanguage() ?: "id") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                text = stringResource(id = R.string.settings_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    SettingSwitchItem(
                        title = stringResource(id = R.string.dark_mode),
                        checked = darkModeState,
                        onCheckedChange = toggleDarkMode
                    )
                }

                item {
                    SettingLanguageItem(
                        selectedLanguage = selectedLanguage,
                        onLanguageChange = {
                            selectedLanguage = it
                            preferencesHelper.setSelectedLanguage(it)
                            onLanguageChange(it)
                        }
                    )
                }

                item {
                    SettingSimpleItem(
                        title = stringResource(id = R.string.notifications),
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Coming Soon")
                            }
                        }
                    )
                }

                item {
                    SettingSimpleItem(
                        title = stringResource(id = R.string.privacy),
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Coming Soon")
                            }
                        }
                    )
                }

                item {
                    SettingSimpleItem(
                        title = stringResource(id = R.string.profile),
                        onClick = {
                            navController.navigate("profile")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingSwitchItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}

@Composable
fun SettingSimpleItem(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun SettingLanguageItem(selectedLanguage: String, onLanguageChange: (String) -> Unit) {
    val languages = listOf(
        "id" to stringResource(id = R.string.language_indonesia),
        "en" to stringResource(id = R.string.language_english)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.language),
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            languages.forEach { (code, label) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLanguageChange(code) }
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedLanguage == code,
                        onClick = { onLanguageChange(code) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
