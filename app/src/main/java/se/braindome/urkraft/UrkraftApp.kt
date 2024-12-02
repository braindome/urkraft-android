package se.braindome.urkraft

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import se.braindome.urkraft.ui.components.UrkraftBottomAppBar
import se.braindome.urkraft.ui.home.HomeScreen
import se.braindome.urkraft.ui.profile.ProfileScreen
import se.braindome.urkraft.ui.settings.SettingsScreen
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.workout.PlanningScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrkraftApp() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.CenterStart)
                    ) {
                        Text(
                            text = "Week 666",
                            fontSize = 18.sp,
                        )
                    }
                },
                actions = { /* TODO */ },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Edit")
                    }
                },
                colors = TopAppBarColors(
                    containerColor = Gray80,
                    scrolledContainerColor = Gray60,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                modifier = Modifier.height(90.dp)
            )
        },
        bottomBar = {
            UrkraftBottomAppBar(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Gray60)
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable(UrkraftRoutes.PLANNING.route) { PlanningScreen() }
                composable(UrkraftRoutes.DASHBOARD.route) { HomeScreen() }
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