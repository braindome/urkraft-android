package se.braindome.urkraft

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun UrkraftNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(UrkraftRoutes.TODAY.route) { TodayScreen() }
        composable(UrkraftRoutes.HOME.route) { UrkraftApp() }
        composable(UrkraftRoutes.PROFILE.route) { ProfileScreen() }
        composable(UrkraftRoutes.SETTINGS.route) { SettingsScreen() }
    }
}

