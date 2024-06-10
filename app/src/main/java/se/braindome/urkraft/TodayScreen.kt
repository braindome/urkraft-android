package se.braindome.urkraft

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.braindome.urkraft.model.Exercise
import se.braindome.urkraft.model.Repository
import kotlin.time.Duration.Companion.seconds

@Composable
fun TodayScreen() {

    val todaysExercises = Repository.getFewerExercises().toMutableList()
    val itemList = remember { SnapshotStateList<String>().apply { addAll(listOf("Item 1", "Item 2", "Item 3")) } }

    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )  {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Today's workout")
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(itemList, key = { it }) { item ->
                    SwipeToDismissItem(
                        item = item,
                        itemList = itemList,
                        modifier = Modifier.animateItem(tween(300))
                    )
                }
            }

        }

    }
}

fun removeItem(item: String, itemList: SnapshotStateList<String>) {
    itemList.remove(item)
}

@Composable
fun SwipeToDismissItem(
    item: String,
    //onRemove: () -> Unit,
    itemList: SnapshotStateList<String>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                coroutineScope.launch {
                    delay(1.seconds)
                    //itemList.remove(item)
                    removeItem(item, itemList)
                }
                true
            } else false
        }
    )
    
    SwipeToDismissBox(
        state = swipeToDismissState, 
        backgroundContent = {
            val backgroundColor by animateColorAsState(
                targetValue = when (swipeToDismissState.currentValue) {
                    SwipeToDismissBoxValue.StartToEnd -> Color.Red
                    SwipeToDismissBoxValue.EndToStart -> Color.Transparent
                    SwipeToDismissBoxValue.Settled -> Color.Transparent
                },
                label = "Animate bg color")
            Box(modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
            )
        },
        modifier = modifier
    ) {
        Card {
            ListItem(headlineContent = { Text(text = item) })
        }
        HorizontalDivider()
        //TodayExerciseRow(item)
    }

}

@Composable
fun TodayExerciseRow(exercise: Exercise) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = exercise.name)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${exercise.sets}x${exercise.reps}")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${exercise.weight} kg")
        }
        Row {
            for (i in 1..exercise.sets) {
                val checkedState = remember { mutableStateOf(false) }
                Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
            }
        }
        HorizontalDivider(thickness = 2.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    TodayScreen()
}

@Preview(showBackground = true)
@Composable
fun TodayExerciseRowPreview() {
    TodayExerciseRow(Exercise(name = "Bench press", sets = 3, reps = 8, weight = 80f))
}