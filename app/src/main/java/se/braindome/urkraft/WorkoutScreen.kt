package se.braindome.urkraft

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.model.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen() {
    val exercises = remember { mutableStateListOf<Exercise>() }
    var openDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Workout") },
            actions = {
                IconButton(onClick = { /* TODO: Implement edit workout */ }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit workout")
                }
                IconButton(onClick = { openDialog = true }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add exercise")
                }
            }
        )
        if (!exercises.isEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    exercises.forEach { exercise ->
                        val completedSets = remember { mutableStateOf(List(exercise.sets) { false }) }
                        ExerciseRow(exercise, completedSets)
                    }
                }
            }

        } else {
            Text("No exercises added")
        }

        if (openDialog) {
            AddExerciseDialog(
                onExerciseAdded = { exercise ->
                    exercises.add(exercise)
                    openDialog = false
                },
                onDismissRequest = { openDialog = false },
            )
        }


    }
}

@Composable
fun AddExerciseDialog(
    onExerciseAdded: (Exercise) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var name by remember { mutableStateOf("")}
    var sets by remember { mutableStateOf("")}
    var reps by remember { mutableStateOf("")}
    var weight by remember { mutableStateOf("")}

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Add Exercise") },
        confirmButton = {
            IconButton(onClick = {
                onExerciseAdded(
                    Exercise(
                        name = name,
                        sets = sets.toInt(),
                        reps = reps.toInt(),
                        weight = weight.toFloat()
                    )
                )
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            IconButton(onClick = onDismissRequest, modifier = Modifier.width(80.dp)) {
                Text("Cancel")
            }
        },
        text = {
            Column(modifier = Modifier.padding(8.dp)) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name", style = TextStyle(fontSize = 14.sp)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = sets,
                        onValueChange = { sets = it },
                        label = { Text("Sets", style = TextStyle(fontSize = 14.sp)) },
                        modifier = Modifier.weight(0.3f)
                    )
                    TextField(
                        value = reps,
                        onValueChange = { reps = it },
                        label = { Text("Reps", style = TextStyle(fontSize = 14.sp)) },
                        modifier = Modifier.weight(0.3f)
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight", style = TextStyle(fontSize = 14.sp)) },
                )
            }
        },
        modifier = Modifier
            .height(400.dp)
            .padding(8.dp)
    )

}

@Composable
fun ExerciseRow(exercise: Exercise, completedSets: MutableState<List<Boolean>>) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = exercise.name, style = MaterialTheme.typography.labelSmall)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(text = "Sets", style = MaterialTheme.typography.labelSmall)
                        Text(text = "${exercise.sets}", style = MaterialTheme.typography.labelSmall)
                    }
                    Column {
                        Text(text = "Reps", style = MaterialTheme.typography.labelSmall)
                        Text(text = "${exercise.reps}", style = MaterialTheme.typography.labelSmall)
                    }
                    Column {
                        Text(text = "Weight", style = MaterialTheme.typography.labelSmall)
                        Text(text = "${exercise.weight}", style = MaterialTheme.typography.labelSmall)
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    IconButton(
                        onClick = { /* TODO: Implement edit exercise */ },
                        modifier = Modifier.weight(0.3f)
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit exercise")
                    }
                    IconButton(
                        onClick = { /* TODO: Implement remove exercise */ },
                        modifier = Modifier.weight(0.3f)
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Remove exercise")
                    }
                }
            }
//            Row {
//                for (i in 0 until exercise.sets) {
//                    Checkbox(
//                        checked = completedSets.value[i],
//                        onCheckedChange = { isChecked ->
//                            completedSets.value = completedSets.value.toMutableList().also { it[i] = isChecked }
//                        },
//                        modifier = Modifier.padding(horizontal = 4.dp)
//                    )
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutScreenPreview() {
    WorkoutScreen()
}

@Preview(showBackground = true)
@Composable
fun AddExerciseDialogPreview() {
    AddExerciseDialog({}, {})
}

@Preview(showBackground = true)
@Composable
fun ExerciseRowPreview() {
    ExerciseRow(
        exercise = Exercise(name = "Bench Press", sets = 3, reps = 10, weight = 50f),
        completedSets = remember { mutableStateOf(List(3) { false }) }
    )
}