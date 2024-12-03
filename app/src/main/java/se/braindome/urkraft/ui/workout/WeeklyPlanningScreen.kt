package se.braindome.urkraft.ui.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.theme.Gray10

@Composable
fun WeeklyPlanningScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeekSelectorBar()
            WeeklyCard(21)
        }
    }
}

@Composable
fun WeekSelectorBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* TODO: Show previous week */ }) {
            Icon(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
                contentDescription = null,
                tint = Gray10
            )
        }
        Text(text = "Week 21", color = Gray10, fontSize = 22.sp)
        IconButton(onClick = { /* TODO: Show upcoming week */ }) {
            Icon(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null,
                tint = Gray10
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WeekSelectorBarPreview() {
    WeekSelectorBar()
}

@Preview(showBackground = true)
@Composable
fun WeeklyPlanningScreenPreview() {
    WeeklyPlanningScreen()
}