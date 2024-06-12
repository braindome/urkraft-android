package se.braindome.urkraft

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.braindome.urkraft.model.Exercise
import se.braindome.urkraft.model.Repository
import se.braindome.urkraft.util.ColorSaver
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreen() {

    val todaysExercises = Repository.getFewerExercises().toMutableList()
    val itemList = remember { SnapshotStateList<Exercise>().apply { addAll(todaysExercises) } }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface),
    )  {
        
        Box(modifier = Modifier.fillMaxSize()) {
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
                    items(itemList, key = { it.id }) { item ->
                        SwipeToDismissItem(
                            item = item,
                            itemList = itemList,
                            modifier = Modifier
                                .animateItem(spring(200F))

                        )
                    }
                }
            }
            
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                ) {
                    AddExerciseScreen()
                }
            }
            
            FloatingActionButton(
                onClick = { showBottomSheet = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add exercise")
            }
        }
    }
}

fun removeItem(item: Exercise, itemList: SnapshotStateList<Exercise>) {
    itemList.remove(item)
}

@Composable
fun AddExerciseScreen() {
    var exerciseName by rememberSaveable { mutableStateOf("") }
    var sets by rememberSaveable { mutableStateOf(3) }
    var reps by rememberSaveable { mutableStateOf(8) }
    var weight by rememberSaveable { mutableStateOf(60f) }
    var exerciseColor by rememberSaveable(saver = ColorSaver) { mutableStateOf(Color.Red) } // Default color
    var showColorPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add exercise",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            Surface(
                modifier = Modifier
                    .background(exerciseColor)
                    .size(56.dp)
                    .clickable {
                        showColorPicker = true
                    },
                color = exerciseColor
            ) { }
            TextField(
                value = exerciseName,
                onValueChange = { exerciseName = it },
                label = { Text("Exercise name") },
                placeholder = { Text("Enter exercise name") },
                modifier = Modifier.fillMaxWidth()
            )
        }


        
        Spacer(modifier = Modifier.padding(8.dp))
        
        Row {
            TextField(
                value = sets.toString(), 
                onValueChange = { sets = it.toIntOrNull() ?: sets },
                label = { Text("Sets") },
                placeholder = { Text("Enter number of sets") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = reps.toString(),
                onValueChange = { reps = it.toIntOrNull() ?: reps },
                label = { Text("Reps") },
                placeholder = { Text("Enter number of reps") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = weight.toString(),
                onValueChange = { weight = it.toFloatOrNull() ?: weight},
                label = { Text("Weight") },
                placeholder = { Text("Enter weight") },
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.padding(8.dp))
        
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Confirm")
        }

    }

    if (showColorPicker) {
        ColorPickerMenu(
            showMenu = { showColorPicker },
            onDismiss = { showColorPicker = false },
            onColorSelected = { selectedColor ->
                exerciseColor = selectedColor
                showColorPicker = false
            }
        )
    }
}

@Composable
fun ColorPickerMenu(
    showMenu: () -> Boolean,
    onDismiss: () -> Unit,
    onColorSelected: (Color) -> Unit
) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropdownMenu(
            expanded = showMenu(),
            onDismissRequest = onDismiss
        ) {
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color)
                        .clickable { onColorSelected(color) }
                )
            }
        }
    }
}

@Composable
fun SwipeToDismissItem(
    item: Exercise,
    itemList: SnapshotStateList<Exercise>,
    modifier: Modifier = Modifier
) {
    var isRemoved by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                coroutineScope.launch {
                    delay(500)
                    //itemList.remove(item)
                    isRemoved = true
                    removeItem(item, itemList)
                }
                true
            } else false
        }
    )

    AnimatedVisibility(
        visible = !isRemoved,
        enter = fadeIn(),
        exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = swipeToDismissState,
            backgroundContent = {
                val backgroundColor by animateColorAsState(
                    targetValue = Color.Transparent,
                    label = "Animate bg color"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    // Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                }
            },
            modifier = modifier
        ) {
            TodayExerciseRow(item)
        }

    }

}

@Composable
fun TodayExerciseRow(exercise: Exercise) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            //modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = exercise.name)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${exercise.sets}x${exercise.reps}")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${exercise.weight} kg")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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