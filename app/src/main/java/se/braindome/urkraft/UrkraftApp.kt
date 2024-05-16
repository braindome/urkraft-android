package se.braindome.urkraft

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrkraftApp() {

    /*
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Text("Drawer Content")
                }
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "home",
                        //tint = Color.
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Home.screen) {
                            popUpTo(0)
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Profile") },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "profile",
                        //tint = Color.
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Profile.screen) {
                            popUpTo(0)
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Settings") },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        //tint = Color.
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Settings.screen) {
                            popUpTo(0)
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Log Out") },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "logout",
                        //tint = Color.
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        },
        gesturesEnabled = true,
        drawerState = drawerState

    ) {
        Scaffold(
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                TopAppBar(
                    title = { Text(text = "Top App Bar") },
                    colors = TopAppBarDefaults.topAppBarColors(),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Rounded.Menu, contentDescription = "menu_button"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.Settings, contentDescription = "search")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Create, contentDescription = "create")
                        }
                    }

                )
            }
        ) { paddingValues ->
            NavHost(navController = navigationController,
                startDestination = Screens.Home.screen) {
                composable(Screens.Home.screen) { WorkoutScreen() }
                composable(Screens.Profile.screen) { Profile() }
                composable(Screens.Settings.screen) {Settings() }
            }
        }
    }

     */

    //                                 WorkoutScreen()
    Column {
        WorkoutCard()
        Spacer(modifier = Modifier.height(20.dp))
        WorkoutCard()
    }
}

@Preview(showBackground = true)
@Composable
fun UrkraftAppPreview() {
    UrkraftApp()
}