package se.braindome.urkraft.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import se.braindome.urkraft.R
import se.braindome.urkraft.UrkraftRoutes
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange60

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
        containerColor = Gray80,
        contentColor = Gray20
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = if (selectedItem == index) {
                            Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .border(1.dp, Orange60, RoundedCornerShape(10.dp))
                        } else {
                            Modifier
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = i),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
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