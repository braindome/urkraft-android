package se.braindome.urkraft.components

import androidx.compose.foundation.layout.height
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
import se.braindome.urkraft.R
import se.braindome.urkraft.UrkraftRoutes

@Composable
fun UrkraftBottomAppBar(navController: NavHostController) {

    var selectedItem by rememberSaveable {
        mutableIntStateOf(getSelectedIndex(navController.currentDestination?.route))
    }
    val navBarIcons = listOf(
        R.drawable.baseline_dashboard_24,
        R.drawable.baseline_timer_24,
        R.drawable.baseline_fitness_center_24,
        R.drawable.baseline_settings_24
    )

    NavigationBar(
        //modifier = Modifier.height(72.dp)
    ) {
        navBarIcons.forEachIndexed { index, i ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                     selectedItem = index
                    val destination = when (index) {
                        0 -> UrkraftRoutes.DASHBOARD.route
                        1 -> UrkraftRoutes.PROFILE.route
                        2 -> UrkraftRoutes.PLANNING.route
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
        UrkraftRoutes.DASHBOARD.route -> 1
        UrkraftRoutes.PLANNING.route -> 2
        UrkraftRoutes.SETTINGS.route -> 3
        else -> 0
    }
}

@Preview(showBackground = true)
@Composable
fun UrkraftBottomAppBarPreview() {
    UrkraftBottomAppBar(rememberNavController())
}