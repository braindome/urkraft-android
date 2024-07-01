package se.braindome.urkraft

import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun UrkraftBottomAppBar(navController: NavHostController) {

    var selectedItem by rememberSaveable {
        mutableIntStateOf(getSelectedIndex(navController.currentDestination?.route))
    }
    val navBarIcons = listOf(
        R.drawable.baseline_person_24,
        R.drawable.baseline_home_24,
        R.drawable.baseline_today_24,
        R.drawable.baseline_settings_24
    )

    NavigationBar(
        modifier = Modifier.height(72.dp)
    ) {
        navBarIcons.forEachIndexed { index, i ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                     selectedItem = index
                    val destination = when (index) {
                        0 -> UrkraftRoutes.PROFILE.route
                        1 -> UrkraftRoutes.HOME.route
                        2 -> UrkraftRoutes.TODAY.route
                        3 -> UrkraftRoutes.SETTINGS.route
                        else -> "home"
                    }
                    navController.navigate(destination)
                          },
                icon = {
                    Icon(
                        painter = painterResource(id = i),
                        contentDescription = null
                    )
                }
            )
        }
    }
}

fun getSelectedIndex(route: String?): Int {
    return when (route) {
        UrkraftRoutes.PROFILE.route -> 0
        UrkraftRoutes.HOME.route -> 1
        UrkraftRoutes.TODAY.route -> 2
        UrkraftRoutes.SETTINGS.route -> 3
        else -> 0
    }
}

@Preview(showBackground = true)
@Composable
fun UrkraftBottomAppBarPreview() {
    UrkraftBottomAppBar(rememberNavController())
}