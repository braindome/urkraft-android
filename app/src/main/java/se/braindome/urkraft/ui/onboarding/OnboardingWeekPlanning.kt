package se.braindome.urkraft.ui.onboarding

import android.widget.Space
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.R
import se.braindome.urkraft.model.Exercise
import se.braindome.urkraft.model.Repository
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange60
import se.braindome.urkraft.ui.theme.Orange80

@Composable
fun OnboardingWeekPlanning() {
    val cycleWeeks = listOf(1, 2, 3, 4)
    LazyColumn { items(cycleWeeks.size) { week ->
        Column {
            Text("Week $week")
        }
    } }
}

@Composable
fun OnboardingWeekday(day: String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val isExpandedIcon = if (isExpanded) R.drawable.baseline_arrow_drop_up_24 else R.drawable.baseline_arrow_drop_down_24
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Gray40).fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(Orange80)
        ) {
            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(isExpandedIcon),
                    contentDescription = "Add exercise",
                )
            }
            Text(
                text = day,
                fontSize = 20.sp,
                color = Gray80,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { /* Handle delete button click */ },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add exercise",
                )
            }
        }
        if (isExpanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(Gray60).fillMaxWidth().padding(4.dp)
            ) {
                Spacer(modifier = Modifier.width(28.dp))
                Text("Exercise", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(80.dp))
                Text("Sets", fontSize = 20.sp, modifier = Modifier.width(45.dp))
                Text("Reps", fontSize = 20.sp, modifier = Modifier.width(45.dp))
                Text("Weight", fontSize = 20.sp, modifier = Modifier.width(70.dp))
            }

            LazyColumn(
                modifier = Modifier.background(Gray20).fillMaxWidth(),
            ){
                items(Repository.getExercises().size) { index ->
                    OnboardingExercise(Repository.getExercises()[index])
                }
            }
        }

    }
}

@Composable
fun OnboardingExercise(exercise: Exercise) {
    val color = Color.Blue
    Column(
        modifier = Modifier.fillMaxWidth().background(Gray40).padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Gray40)
                .padding(4.dp)
            //.border(1.dp, Gray80, shape = RoundedCornerShape(8.dp))
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 18.dp),
                horizontalArrangement = Arrangement.Start,
            ){
                Text(
                    text = exercise.name,
                    fontSize = 16.sp,
                    modifier = Modifier.width(170.dp)
                )
                Text(
                    text = exercise.sets.toString(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(45.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = exercise.reps.toString(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(45.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = exercise.weight.toString(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(80.dp)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 36.dp, end = 20.dp),
            color = Gray80
        )
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingExercisePreview() {
    OnboardingExercise(Repository.getExercises()[0])
}

@Preview(showBackground = true)
@Composable
fun OnboardingWeekdayPreview() {
    OnboardingWeekday("Monday")
}

@Preview(showBackground = true)
@Composable
fun OnboardingWeekPlanningPreview() {
    OnboardingWeekPlanning()
}