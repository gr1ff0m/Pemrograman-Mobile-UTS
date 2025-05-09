package com.edumate.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.edumate.R
import com.edumate.utils.PreferencesHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, id: String?, preferencesHelper: PreferencesHelper) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.detail_item)) })
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Placeholder for item details
            // Display the id or some other relevant information based on the ID.
            Text(
                text = stringResource(id = R.string.detail_item_message, id ?: "Unknown ID")
            )

            // Optionally, add more content here, like buttons or other data
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Handle back or navigate action here
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.back_to_home))
            }
        }
    }
}
