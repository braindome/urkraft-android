package se.braindome.urkraft.ui.workout

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import se.braindome.urkraft.R
import se.braindome.urkraft.UrkraftRoutes

@Composable
fun DailyWorkoutScreen(
    viewModel: DailyPlanningViewModel,
    navController: NavHostController
) {
    val exercises by viewModel.exercises.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var isAddExerciseScreenOpen by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Today's workout")
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
                        modifier = Modifier
                            .padding(8.dp)
                            .animateItem(spring(200F))

                    )
                }
            }
        }

        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    viewModel.resetExerciseValues()
                    isAddExerciseScreenOpen = true
                    navController.navigate(UrkraftRoutes.ADD_EXERCISE.route)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "add_exercise"
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