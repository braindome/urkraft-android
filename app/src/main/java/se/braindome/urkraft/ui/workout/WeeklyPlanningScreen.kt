package se.braindome.urkraft.ui.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WeeklyPlanningScreen() {
    val numberOfWeeks = 4

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Weekly Planning", style = MaterialTheme.typography.labelLarge)
            LazyColumn {
                items(numberOfWeeks) {
                    WeeklyCard()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyPlanningScreenPreview() {
    WeeklyPlanningScreen()
}