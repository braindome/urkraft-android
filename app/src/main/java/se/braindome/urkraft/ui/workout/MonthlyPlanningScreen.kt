package se.braindome.urkraft.ui.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.theme.Orange60

@Composable
fun MonthlyPlanningScreen() {

        Column(
            //modifier = Modifier.fillMaxSize(),
        ) {
            //MonthSelectorBar()
            GridCalendar()
            //UrkraftDatePicker()
            WorkoutPreview()
        }
        /*
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MonthSelectorBar()
            GridCalendar()

        }

         */

}

@Composable
fun WorkoutPreview() {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(28.dp)
            .clickable { /* TODO: Navigate to workout */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Orange60),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Workout details")
            WorkoutDetailsRow()
            WorkoutDetailsRow()
            WorkoutDetailsRow()
            WorkoutDetailsRow()
            WorkoutDetailsRow()
        }
    }
}

@Composable
fun WorkoutDetailsRow() {
    Row {
        Text(text = "Bench Press ")
        Text(text = "3 sets of ")
        Text(text = "4 reps at ")
        Text(text = "120kg")
    }
}

@Composable
fun MonthSelectorBar() {
    Row(
        modifier = Modifier.fillMaxWidth().height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* TODO: Show previous week */ }) {
            Icon(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
                contentDescription = null
            )
        }
        Text(text = "September")
        IconButton(onClick = { /* TODO: Show upcoming week */ }) {
            Icon(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MonthSelectorBarPreview() {
    MonthSelectorBar()
}

@Preview(showBackground = true)
@Composable
fun MonthlyPlanningScreenPreview() {
    MonthlyPlanningScreen()
}