package com.edumate

import android.os.Bundle
import android.content.res.Configuration
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edumate.ui.flashcards.FlashcardsScreen
import com.edumate.ui.pomodoro.PomodoroScreen
import com.edumate.ui.tasks.TaskManagerScreen
import com.edumate.ui.home.HomeScreen
import com.edumate.ui.login.LoginScreen
import com.edumate.ui.profile.ProfileScreen
import com.edumate.ui.register.RegisterScreen
import com.edumate.ui.settings.SettingsScreen
import com.edumate.ui.theme.EduMateTheme
import com.edumate.utils.PreferencesHelper
import java.util.*
import com.edumate.ui.search.SearchScreen
import com.edumate.ui.createitem.CreateItemScreen
import com.edumate.ui.detail.DetailScreen


class MainActivity : ComponentActivity() {

    private fun setLocale(localeCode: String) {
        val locale = Locale(localeCode)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencesHelper = PreferencesHelper(applicationContext)

        preferencesHelper.setEmail("user@example.com")
        preferencesHelper.setPassword("securePassword123")
        val email = preferencesHelper.getEmail()
        val password = preferencesHelper.getPassword()

        Log.d("MainActivity", "Email: $email, Password: $password")

        val selectedLanguage = ""
        setLocale(selectedLanguage)

        fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            // Handle configuration change (e.g., when orientation changes)
            // You can use this to handle language changes as well
            val preferencesHelper = PreferencesHelper(applicationContext)
            val selectedLanguage = preferencesHelper.getSelectedLanguage() ?: "id"
            setLocale(selectedLanguage)
        }

        setContent {
            val context = LocalContext.current
            val preferencesHelper = PreferencesHelper(context)

            // Get dark mode and other preferences
            val darkModePref = preferencesHelper.isDarkModeEnabled()
            var darkModeState by remember { mutableStateOf<Boolean>(darkModePref) }

            val isRememberMeChecked = preferencesHelper.isRememberMeEnabled()
            val email = preferencesHelper.getEmail()
            val password = preferencesHelper.getPassword()

            val startDestination = if (
                isRememberMeChecked &&
                !email.isNullOrEmpty() &&
                !password.isNullOrEmpty()
            ) {
                "home"
            } else {
                "login"
            }

            // Toggle dark mode
            val toggleDarkMode: (Boolean) -> Unit = { isEnabled ->
                preferencesHelper.setDarkModeEnabled(isEnabled)
                darkModeState = isEnabled
            }

            EduMateTheme(darkTheme = darkModeState) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Navigation between screens
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable("home") { HomeScreen(navController, preferencesHelper) }
                        composable("login") { LoginScreen(navController, preferencesHelper) }
                        composable("register") { RegisterScreen(navController, preferencesHelper) }
                        composable("profile") { ProfileScreen(navController, preferencesHelper) }
                        composable("settings") {
                            SettingsScreen(
                                navController = navController,
                                preferencesHelper = preferencesHelper,
                                toggleDarkMode = toggleDarkMode,
                                darkModeState = darkModeState,
                                onLanguageChange = { newLang ->
                                    setLocale(newLang)
                                    preferencesHelper.setSelectedLanguage(newLang)
                                }
                            )
                        }
                        composable("flashcards") { FlashcardsScreen(navController) }
                        composable("pomodoro") { PomodoroScreen(navController) }
                        composable("tasks") { TaskManagerScreen(navController, preferencesHelper) }
                        composable("search") { SearchScreen(navController, preferencesHelper) }
                        composable("create_item") { CreateItemScreen(
                            navController,
                            preferencesHelper
                        ) }
                        composable("detail/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")
                            DetailScreen(navController, id, preferencesHelper)
                    }
                }
            }
        }
    }

}
}
