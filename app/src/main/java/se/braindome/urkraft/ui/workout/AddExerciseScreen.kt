package se.braindome.urkraft.ui.workout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.braindome.urkraft.model.Exercise
import se.braindome.urkraft.utils.ColorSaver
import timber.log.Timber

@Composable
fun AddExerciseScreen(
    viewModel: DailyPlanningViewModel,
    //onConfirm: () -> Unit,
    navController: NavHostController
)  {

    val uiState by viewModel.uiState.collectAsState()
    var exerciseColor by rememberSaveable(saver = ColorSaver) { mutableStateOf(Color.Red) } // Default color
    var showColorPicker by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
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
                value = uiState.exerciseName,
                onValueChange = { viewModel.updateExerciseName(it) },
                label = { Text("Exercise name") },
                placeholder = { Text("Enter exercise name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            TextField(
                //value = sets?.toString() ?: "",
                value = uiState.sets.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = {
                    if (!it.contains(".") && !it.contains(",")) {
                        it.toIntOrNull()?.let { it1 -> viewModel.updateSets(it1) }
                    }
                },
                label = { Text("Sets") },
                //placeholder = { Text("Enter number of sets") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                //value = reps?.toString() ?: "",
                value = uiState.reps.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = {
                    if (!it.contains(".") && !it.contains(",")) {
                        it.toIntOrNull()?.let { it1 -> viewModel.updateReps(it1) }
                    }
                },
                label = { Text("Reps") },
                //placeholder = { Text("Enter number of reps") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                //value = if (weight == 0f) "" else weight.toString(),
                //value = weight?.takeIf { it != 0f }?.toString() ?: "",
                value = uiState.weight.takeIf { it != 0f }?.let { if (it % 1 == 0f) it.toString() else it.toString() } ?: "",
                onValueChange = {
                    //(it.toFloatOrNull() ?: weight)?.let { it1 -> viewModel.updateWeight(it1) }
                    it.replace(",", ".").toFloatOrNull()?.let { it1 -> viewModel.updateWeight(it1) }
                },
                label = { Text("Weight") },
                //placeholder = { Text("0") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Button(onClick = {
            scope.launch {
                val exercise = Exercise(
                    name = uiState.exerciseName,
                    sets = uiState.sets ?: 0,
                    reps = uiState.reps ?: 0,
                    weight = uiState.weight ?: 0f,
                    color = String.format("#%06X", (0xFFFFFF and exerciseColor.toArgb()))
                )
                viewModel.addExerciseToList(exercise)
                viewModel.resetExerciseValues()
                //onConfirm()
                navController.navigateUp()
            }
        }) {
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
    val colors = listOf(Color.White, Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta)

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
    viewModel: DailyPlanningViewModel,
    modifier: Modifier = Modifier
) {
    var isRemoved by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                coroutineScope.launch {
                    delay(300)
                    viewModel.removeExerciseFromList(item)
                    isRemoved = true
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

    var color = Color.White // Default color
    try {
        color = Color(android.graphics.Color.parseColor(exercise.color)) // Parse color string
    } catch (e: IllegalArgumentException) {
        Timber.tag("TodayExerciseRow").e("Invalid color string: %s", exercise.color)
    }

    Surface(shadowElevation = 4.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(32.dp),
                //.border(1.dp, Color.Black),
                color = color
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = Modifier.padding(start = 14.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = exercise.name)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "${exercise.sets}x${exercise.reps}")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "${exercise.weight} kg")
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy((-16).dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 1.dp, end = 1.dp)
                ) {
                    items(exercise.sets) {
                        val checkedState = remember { mutableStateOf(false) }
                        Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
                    }
                }
                //HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddExerciseScreenPreview() {
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        sheetState.show()
    }
    AddExerciseScreen(
        viewModel = DailyPlanningViewModel(),
        //onConfirm = {},
        navController = rememberNavController()
    )
}

@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    TodayScreen(viewModel = DailyPlanningViewModel())
}

@Preview(showBackground = true)
@Composable
fun TodayExerciseRowPreview() {
    TodayExerciseRow(Exercise(name = "Bench press", sets = 3, reps = 8, weight = 80f))
}