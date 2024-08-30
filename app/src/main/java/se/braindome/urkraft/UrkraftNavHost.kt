package se.braindome.urkraft

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import se.braindome.urkraft.ui.profile.ProfileScreen
import se.braindome.urkraft.ui.settings.SettingsScreen
import se.braindome.urkraft.ui.workout.DailyWorkoutScreen
import se.braindome.urkraft.ui.workout.DailyPlanningViewModel

@Composable
fun UrkraftNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(UrkraftRoutes.PLANNING.route) {
            // TodayScreen(TodayScreenViewModel())
            DailyWorkoutScreen(DailyPlanningViewModel(), navController)
        }
        composable(UrkraftRoutes.DASHBOARD.route) { UrkraftApp() }
        composable(UrkraftRoutes.PROFILE.route) { ProfileScreen() }
        composable(UrkraftRoutes.SETTINGS.route) { SettingsScreen() }
    }
}

