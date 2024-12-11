package se.braindome.urkraft.ui.workout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import se.braindome.urkraft.ui.components.CheckboxState
import se.braindome.urkraft.ui.components.DecimalTextField
import se.braindome.urkraft.ui.components.NumericInputType
import se.braindome.urkraft.ui.components.SetCheckbox
import se.braindome.urkraft.ui.components.NumericTextField
import se.braindome.urkraft.ui.components.TextButton
import se.braindome.urkraft.ui.theme.Gray10
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.utils.ColorSaver
import timber.log.Timber
import java.util.UUID

@Composable
fun AddExerciseScreen(
    viewModel: DailyPlanningViewModel,
    isEditing: Boolean = false,
    navController: NavHostController,
    exerciseToEditId: String? = null
)  {

    val uiState by viewModel.uiState.collectAsState()
    var exerciseColor by rememberSaveable(saver = ColorSaver) { mutableStateOf(Color.Red) } // Default color
    var showColorPicker by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(exerciseToEditId) {
        val exerciseToEdit = viewModel.getExerciseById(exerciseToEditId)
        exerciseToEdit?.let {
            viewModel.updateExerciseName(it.name)
            viewModel.updateSets(it.sets)
            viewModel.updateReps(it.reps)
            viewModel.updateWeight(it.weight.toString())
            exerciseColor = Color(android.graphics.Color.parseColor(it.color))
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (isEditing) "Edit exercise" else "Add exercise",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall,
            color = Gray10
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .background(exerciseColor)
                        .size(56.dp)
                        .clickable {
                            showColorPicker = true
                        },
                    color = exerciseColor,
                ) { }
                TextField(
                    value = uiState.exerciseName,
                    onValueChange = { viewModel.updateExerciseName(it) },
                    label = { Text("Exercise name") },
                    placeholder = { Text("Enter exercise name") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Gray20,
                        unfocusedContainerColor = Gray20,
                        disabledContainerColor = Gray40,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black

                    ),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            NumericTextField(
                value = uiState.sets.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = {
                    if (it.isEmpty()) {
                        viewModel.updateSets(0)
                    } else {
                        val newValue = it.toIntOrNull()
                        if (newValue != null && newValue in 1..20) {
                            viewModel.updateSets(newValue)
                        }
                    }
                },
                inputType = NumericInputType.SETS,
                modifier = Modifier.weight(1f)
            )
            /*
            TextField(
                value = uiState.sets.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { viewModel.updateSets(it.toIntOrNull() ?: 0) },
                label = { Text("Sets") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Gray20,
                    unfocusedContainerColor = Gray20,
                    disabledContainerColor = Gray40,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black

                ),
                shape = RoundedCornerShape(8.dp)
            )*/
            Spacer(modifier = Modifier.width(8.dp))
            NumericTextField(
                value = uiState.reps.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = {
                    if (it.isEmpty()) {
                        viewModel.updateReps(0)
                    } else {
                        val newValue = it.toIntOrNull()
                        if (newValue != null && newValue in 1..50) {
                            viewModel.updateReps(newValue)
                        }
                    }
                },
                inputType = NumericInputType.REPS,
                modifier = Modifier.weight(1f)
            )
            /*
            TextField(
                value = uiState.reps.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { viewModel.updateReps(it.toIntOrNull() ?: 0) },
                label = { Text("Reps") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Gray20,
                    unfocusedContainerColor = Gray20,
                    disabledContainerColor = Gray40,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            )
            */
            Spacer(modifier = Modifier.width(8.dp))
            DecimalTextField(
                value =  if (uiState.weight == "0.0") "" else uiState.weight.removeSuffix(".0"),
                onValueChange = {
                    val sanitizedInput = it.replace(',', '.')
                    if (sanitizedInput.isEmpty() || sanitizedInput == "." || sanitizedInput.toDoubleOrNull() != null ) {
                        viewModel.updateWeight(sanitizedInput)
                    }
                },
                modifier = Modifier.weight(1f),
            )
            /*
            TextField(
                value =  if (uiState.weight == "0.0") "" else uiState.weight.removeSuffix(".0"),
                onValueChange = {
                    val sanitizedInput = it.replace(',', '.')
                    if (sanitizedInput.isEmpty() || sanitizedInput == "." || sanitizedInput.toDoubleOrNull() != null ) {
                        viewModel.updateWeight(sanitizedInput)
                    }
                },
                label = { Text("Weight") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Gray20,
                    unfocusedContainerColor = Gray20,
                    disabledContainerColor = Gray40,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black

                ),
                shape = RoundedCornerShape(8.dp)
            )

             */
        }

        Spacer(modifier = Modifier.padding(8.dp))

        TextButton(onClick = {
            scope.launch {
                val exercise = Exercise(
                    id = exerciseToEditId ?: UUID.randomUUID().toString(),
                    name = uiState.exerciseName,
                    sets = uiState.sets ?: 0,
                    reps = uiState.reps ?: 0,
                    weight = uiState.weight.toDoubleOrNull() ?: 0.0,
                    color = String.format("#%06X", (0xFFFFFF and exerciseColor.toArgb()))
                )
                if (isEditing) {
                    viewModel.updateExerciseInList(exercise)
                } else {
                    viewModel.addExerciseToList(exercise)
                }
                //viewModel.resetExerciseValues()
                //onConfirm()
                navController.navigateUp()
            }
        },
            label = if (isEditing) "Save" else "Add",

        )
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
    navController: NavHostController,
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
            TodayExerciseRow(
                exercise = item,
                onLongPress = {navController.navigate("edit_exercise/${item.id}")}
            )
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodayExerciseRow(exercise: Exercise, onLongPress: (Exercise) -> Unit) {

    var color = Color.Blue // Default color
    try {
        color = Color(android.graphics.Color.parseColor(exercise.color)) // Parse color string
    } catch (e: IllegalArgumentException) {
        Timber.tag("TodayExerciseRow").e("Invalid color string: %s", exercise.color)
    }

    Surface(
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.combinedClickable(
            onClick = {},
            onLongClick = { onLongPress(exercise) }
        )
    ) {
        Row(
            
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Gray60)
                .border(BorderStroke(1.dp, Color.Transparent), RoundedCornerShape(8.dp))

        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(32.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp)),
                color = color
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
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
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 1.dp, end = 1.dp)
                ) {
                    items(exercise.sets) {
                        val checkedState = remember { mutableStateOf(CheckboxState.EMPTY) }
                        SetCheckbox(state = checkedState.value, onStateChange = { checkedState.value = it })
                    }
                }
                //HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFF2A2A2A)
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
    Box(modifier = Modifier.background(Gray80)) {
        TodayScreen(viewModel = DailyPlanningViewModel())

    }
}

@Preview(showBackground = true)
@Composable
fun TodayExerciseRowPreview() {
    TodayExerciseRow(Exercise(name = "Bench press", sets = 3, reps = 8, weight = 80.0), onLongPress = {})
}