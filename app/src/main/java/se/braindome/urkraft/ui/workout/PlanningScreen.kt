package se.braindome.urkraft.ui.workout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class PlanningScreenRoutes(val route: String) {
    data object Daily : PlanningScreenRoutes("daily")
    data object Weekly : PlanningScreenRoutes("weekly")
    data object Monthly : PlanningScreenRoutes("monthly")
    data object NewExercise : PlanningScreenRoutes("new_exercise")
}

@Composable
fun PlanningScreen() {
    val navController = rememberNavController()
    val dailyPlanningViewModel: DailyPlanningViewModel = viewModel()
    Scaffold(
        topBar = {
            PlanningTopNavBar(navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = PlanningScreenRoutes.Daily.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(PlanningScreenRoutes.Daily.route) {
                DailyWorkoutScreen(dailyPlanningViewModel, navController)
            }
            composable(PlanningScreenRoutes.Weekly.route) {
                WeeklyPlanningScreen()
            }
            composable(PlanningScreenRoutes.Monthly.route) {
                MonthlyPlanningScreen()
            }
            composable(PlanningScreenRoutes.NewExercise.route) {
                AddExerciseScreen(dailyPlanningViewModel, navController)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanningTopNavBar(navController: NavHostController) {
    val planningRoutes = listOf(
        PlanningScreenRoutes.Daily,
        PlanningScreenRoutes.Weekly,
        PlanningScreenRoutes.Monthly
    )
    var selected by rememberSaveable {
        mutableIntStateOf(0)
    }
    SecondaryTabRow(selectedTabIndex = selected) {
        planningRoutes.forEachIndexed { index,  route ->
            Tab(
                text = { Text(route.route) },
                selected = selected == index,
                onClick = {
                    selected = index
                    navController.navigate(route.route)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanningTopNavBarPreview() {
    PlanningTopNavBar(rememberNavController())
}