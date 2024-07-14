package se.braindome.urkraft

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import se.braindome.urkraft.model.Repository

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