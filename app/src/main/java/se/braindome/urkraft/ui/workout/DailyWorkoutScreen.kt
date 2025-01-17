package se.braindome.urkraft.ui.workout

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import se.braindome.urkraft.R
import se.braindome.urkraft.UrkraftRoutes
import se.braindome.urkraft.ui.theme.Orange60

@Composable
fun DailyWorkoutScreen(
    viewModel: DailyPlanningViewModel,
    navController: NavHostController
) {
    val exercises by viewModel.exercises.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var isAddExerciseScreenOpen by rememberSaveable { mutableStateOf(false) }
    val isWorkoutAvailable by rememberSaveable { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isWorkoutAvailable) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Today's workout", fontSize = 24.sp, color = Orange60)
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(bottom = 8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(exercises, key = { it.id }) { item ->
                        SwipeToDismissItem(
                            item = item,
                            viewModel = viewModel,
                            navController = navController,
                            modifier = Modifier
                                .padding(8.dp)
                                .animateItem(spring(200F))

                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize().padding(50.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "No workout available. Please add an exercise to start.",
                    fontSize = 24.sp,
                    color = Orange60
                )
            }
        }


        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    viewModel.resetExerciseValues()
                    isAddExerciseScreenOpen = true
                    navController.navigate(
                        PlanningScreenRoutes.NewExercise.route
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            containerColor = Orange60,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "add_exercise",
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWorkoutScreenPreview() {
    DailyWorkoutScreen(
        viewModel = DailyPlanningViewModel(),
        navController = rememberNavController()
    )
}