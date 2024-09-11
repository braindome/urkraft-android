package se.braindome.urkraft.ui.workout

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.model.CalenderRepository
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GridCalendar() {
    val currentDate = remember { mutableStateOf(Date()) }
    val daysInCurrentMonth = CalenderRepository.generateDaysForMonth(currentDate.value)
    val weeksInCurrentMonth = CalenderRepository.generateWeeksForMonth(currentDate.value)

    Timber.tag("Days in month").d(daysInCurrentMonth.toString())
    Column(
        modifier = Modifier
            .size(550.dp)
            .padding(end = 16.dp, start = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonthNavigation(currentDate = currentDate) { newDate ->
            currentDate.value = newDate

        }
        WeekDaysRow()
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (week in weeksInCurrentMonth) {
                WeekRow(week = week)
            }
        }
    }

}

@Composable
fun MonthNavigation(
    currentDate: MutableState<Date>,
    onMonthChange: (Date) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate.value

    val monthName = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(currentDate.value)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = {
            calendar.add(Calendar.MONTH, -1)
            currentDate.value = calendar.time
            onMonthChange(calendar.time)
        }) {
            Text("<")
        }
        Text(
            text = monthName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(onClick = {
            calendar.add(Calendar.MONTH, 1)
            currentDate.value = calendar.time
            onMonthChange(calendar.time)
        }) {
            Text(">")
        }
    }
}

@Composable
fun WeekDaysRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        val days = listOf("M", "T", "W", "T", "F", "S", "S")
        days.forEach { day ->
            Text(
                text = day,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun WeekRow(week: CalenderRepository.Week) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        WeekNumberCell(weekNumber = week.weekNumber)
        week.dates.forEach { date ->
            DayCell(day = date.dayOfMonth, isCurrentMonth = date.isCurrentMonth)
        }
    }
}

@Composable
fun DayCell(day: Int, isCurrentMonth: Boolean) {
    val backgroundColor = if (isCurrentMonth) Color.White else Color.LightGray

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(color = backgroundColor)
            .clip(shape = RoundedCornerShape(5.dp))
            .border(1.dp, Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            fontSize = 18.sp
        )
    }
}

@Composable
fun WeekNumberCell(weekNumber: Int) {
    // Display the week number
    Box(
        modifier = Modifier
            .size(height = 24.dp, width = 24.dp)
            .clip(shape = CircleShape)
            .background(color = Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = weekNumber.toString(),
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun DayCellPreview() {
    DayCell(day = 1, isCurrentMonth = true)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GridCalendarPreview() {
    GridCalendar()
}