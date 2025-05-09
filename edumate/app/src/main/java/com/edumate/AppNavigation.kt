package com.edumate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edumate.ui.home.HomeScreen
import com.edumate.ui.tasks.TaskManagerScreen
import com.edumate.ui.createitem.CreateItemScreen
import com.edumate.ui.search.SearchScreen
import com.edumate.ui.detail.DetailScreen
import com.edumate.utils.PreferencesHelper

@Composable
fun AppNavigation(navController: NavHostController, preferencesHelper: PreferencesHelper) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, preferencesHelper)
        }
        composable("task_manager") {
            TaskManagerScreen(navController, preferencesHelper)
        }
        composable("create_item") {
            CreateItemScreen(navController, preferencesHelper)
        }
        composable("search") {
            SearchScreen(navController, preferencesHelper)
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(navController, id, preferencesHelper)
        }

    }
}
