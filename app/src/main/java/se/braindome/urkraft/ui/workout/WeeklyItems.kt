package se.braindome.urkraft.ui.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import se.braindome.urkraft.model.Repository
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Orange60

@Composable
fun WeeklyCard(weekNumber: Int) {
    val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")
    ElevatedCard(
        colors = CardColors(
            containerColor = Gray20,
            contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),

            ) {
            Text(
                text = "Week $weekNumber",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            weekDays.forEachIndexed {index, day ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = day,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp)
                    )
                    WeeklyCardItem()
                }
                if (index != weekDays.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .height(1.dp)
                            .padding(start = 32.dp, end = 8.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyCardItem() {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        WorkoutDialog(onDismissRequest = { showDialog = false } )
    }
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.height(56.dp)
            .fillMaxWidth()
            .padding(bottom = 8.dp),
    ) {
        Text(
            lineHeight = 32.sp,
            text = "This is a workout",
            fontSize = 22.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(15.dp))
                .background(Orange60)
                .fillMaxWidth()
                .height(48.dp)
                .clickable { showDialog = true }
                .padding(8.dp)

        )
    }
}

@Composable
fun WorkoutDialog(onDismissRequest: () -> Unit = {}) {
    val mockWorkout = Repository.getWorkouts()[0]
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(250.dp)
                .background(Gray40)
                .padding(8.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    //.padding(8.dp)
                    //.background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(text = "Weekday", fontSize = 16.sp)
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    mockWorkout.exercises.forEach { exercise ->

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = exercise.name, fontSize = 12.sp, modifier = Modifier.weight(0.5f))
                            Text(text = "${exercise.sets}  x", fontSize = 12.sp)
                            Text(text = "${exercise.reps}  x", fontSize = 12.sp)
                            Text(text = "${exercise.weight} kg", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutDialogPreview() {
    WorkoutDialog()
}

@Preview(showBackground = true)
@Composable
fun WeeklyCardPreview() {
    WeeklyCard(weekNumber = 21)
}