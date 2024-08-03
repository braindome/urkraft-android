package se.braindome.urkraft

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import se.braindome.urkraft.components.UrkraftBottomAppBar
import se.braindome.urkraft.ui.home.HomeScreen
import se.braindome.urkraft.ui.profile.ProfileScreen
import se.braindome.urkraft.ui.settings.SettingsScreen
import se.braindome.urkraft.ui.workout.AddExerciseScreen
import se.braindome.urkraft.ui.workout.CurrentWorkoutScreen
import se.braindome.urkraft.ui.workout.TodayScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrkraftApp() {

    val navController = rememberNavController()
    val currentWorkoutViewModel = TodayScreenViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Week 666") },
                actions = { /* TODO */ },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Edit")
                    }
                }
            )
        },
        bottomBar = {
            UrkraftBottomAppBar(navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            NavHost(navController = navController, startDestination = "home") {
                composable(UrkraftRoutes.TODAY.route) {
                    //TodayScreen(currentWorkoutViewModel)
                    CurrentWorkoutScreen(currentWorkoutViewModel, navController)
                }
                composable(UrkraftRoutes.ADD_EXERCISE.route) {
                    AddExerciseScreen(
                        currentWorkoutViewModel,
                        navController,
                    )
                }
                composable(UrkraftRoutes.HOME.route) { HomeScreen() }
                composable(UrkraftRoutes.PROFILE.route) { ProfileScreen() }
                composable(UrkraftRoutes.SETTINGS.route) { SettingsScreen() }

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun UrkraftAppPreview() {
    UrkraftApp()
}