package se.braindome.urkraft

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.model.Repository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrkraftApp() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Week 666") },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit microcycle")
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Remove microcycle")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add workout")
            }
        }
    ) { paddingValues ->
        val workouts = Repository.getWorkouts()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(workouts) { workout ->
                WorkoutCard(workout = workout)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UrkraftAppPreview() {
    UrkraftApp()
}