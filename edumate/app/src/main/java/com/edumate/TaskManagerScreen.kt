package com.edumate.ui.tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.res.stringResource
import com.edumate.R
import com.edumate.utils.PreferencesHelper

@Composable
fun TaskManagerScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    var tasks = remember { mutableStateListOf<String>() }  // List tugas yang bersifat reaktif
    var newTask by remember { mutableStateOf("") }
    var editingIndex by remember { mutableStateOf(-1) }
    var searchQuery by remember { mutableStateOf("") }  // State untuk pencarian

    // Ambil pengaturan bahasa yang dipilih
    val selectedLanguage = preferencesHelper.getSelectedLanguage() ?: "id"

    // Fungsi untuk menyaring tugas berdasarkan pencarian dan bahasa
    fun filterTasks(query: String) {
        // Filter task berdasarkan query pencarian
        tasks.filter { task ->
            task.contains(query, ignoreCase = true)  // Pencarian tanpa membedakan huruf kapital
        }.let { filtered ->
            // Memperbarui list filteredTasks dengan data yang sudah difilter
            tasks.clear()  // Clear data yang ada
            tasks.addAll(filtered)  // Menambah data hasil filter
        }
    }

    // Kolom utama
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = stringResource(id = R.string.task_manager),  // Gunakan string resource
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                filterTasks(it)
            },
            label = { Text("Search Tasks") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input task field
        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text(if (editingIndex >= 0) stringResource(id = R.string.edit_task) else stringResource(id = R.string.new_task)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (newTask.isNotBlank()) {
                    if (editingIndex >= 0 && editingIndex < tasks.size) {
                        tasks[editingIndex] = newTask
                        editingIndex = -1
                    } else {
                        tasks.add(newTask)
                    }
                    newTask = ""
                    filterTasks(searchQuery)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingIndex >= 0) stringResource(id = R.string.update_task) else stringResource(id = R.string.add_task))
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(modifier = Modifier
            .weight(1f)
            .fillMaxSize()) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(task)
                        Row {
                            IconButton(onClick = {
                                val taskIndex = tasks.indexOf(task)
                                if (taskIndex >= 0) {
                                    editingIndex = taskIndex
                                    newTask = task
                                }
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit")
                            }

                            IconButton(onClick = {
                                val taskIndex = tasks.indexOf(task)
                                if (taskIndex >= 0) {
                                    tasks.removeAt(taskIndex)
                                    if (editingIndex == taskIndex) {
                                        editingIndex = -1
                                        newTask = ""
                                    }
                                    filterTasks(searchQuery)
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }

                            IconButton(onClick = {
                                navController.navigate("detail/${task.hashCode()}")
                            }) {
                                Icon(Icons.Default.Info, contentDescription = "Detail")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(stringResource(id = R.string.back_to_home))
        }
    }
}
