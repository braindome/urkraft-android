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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import se.braindome.urkraft.R
import timber.log.Timber

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreen(viewModel: DailyPlanningViewModel) {

    val exercises by viewModel.exercises.collectAsState()
    Timber.d("Today's exercises: $exercises")

    val navController = rememberNavController()


    BottomSheetScaffold(
        sheetContent = {
            AddExerciseScreen(
                viewModel = viewModel,
                //onConfirm = { isSheetOpen = false },
                navController = navController
            )
        },
        sheetPeekHeight = 40.dp,
        sheetDragHandle = { Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = null ) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
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
                            navController = navController,
                            modifier = Modifier
                                .padding(8.dp)
                                .animateItem(spring(200F))

                        )
                    }
                }
            }

        }
    }

}


 */
