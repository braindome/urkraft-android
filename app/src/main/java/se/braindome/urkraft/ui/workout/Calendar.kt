package se.braindome.urkraft.ui.workout

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun GridCalendar() {
    val currentDate = remember { mutableStateOf(Date()) }
    val selectedDate = remember { mutableStateOf<Date?>(null) }
    val daysInCurrentMonth = CalenderRepository.generateDaysForMonth(currentDate.value)
    val weeksInCurrentMonth = CalenderRepository.generateWeeksForMonth(currentDate.value)

    Timber.tag("Days in month").d(daysInCurrentMonth.toString())
    Column(
        modifier = Modifier.fillMaxSize(),
            //.size(550.dp),
            //.padding(end = 16.dp, start = 16.dp),
        verticalArrangement = Arrangement.Top,
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
                WeekRow(week = week, selectedDate = selectedDate)
            }
        }
        WorkoutPreview()
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
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Next month",
                tint = Orange80,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = monthName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            color = Orange80
        )
        IconButton(onClick = {
            calendar.add(Calendar.MONTH, 1)
            currentDate.value = calendar.time
            onMonthChange(calendar.time)
        }) {
            //Text(">", color = Orange80, modifier = Modifier.size(24.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next month",
                tint = Orange80,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun WeekDaysRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(start = 0.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.size(26.dp))
        val days = listOf("M", "T", "W", "T", "F", "S", "S")
        days.forEach { day ->
            Text(
                text = day,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                color = Orange80
            )
        }
    }
}

@Composable
fun WeekRow(week: CalenderRepository.Week, selectedDate: MutableState<Date?>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth().padding(2.dp)
            //.padding(end = 4.dp, start = 4.dp)
    ) {
        WeekNumberCell(weekNumber = week.weekNumber)
        week.dates.forEach { dateInfo ->
            DayCell(day = dateInfo.dayOfMonth, isCurrentMonth = dateInfo.isCurrentMonth, date = dateInfo.date, selectedDate = selectedDate)
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun DayCell(day: Int, isCurrentMonth: Boolean, date: Date, selectedDate: MutableState<Date?>) {
    var isSelected = selectedDate.value == date
    val backgroundColor = when {
        isSelected -> Orange80
        isCurrentMonth -> Gray40
        else -> Gray80
    }

    Surface(
        modifier = Modifier
            .size(48.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable { selectedDate.value = if (isSelected) null else date },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)

        ) {
            Text(
                text = day.toString(),
                fontSize = 18.sp,
                color = if (isSelected) Color.Black else if (isCurrentMonth) Orange80 else Gray40,
            )
        }

    }
}

@Composable
fun WeekNumberCell(weekNumber: Int) {
    // Display the week number
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(height = 24.dp, width = 24.dp)
            .clip(shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = weekNumber.toString(),
            fontSize = 14.sp,
            color = Orange80
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrkraftDatePicker() {
    DatePicker(
        state = rememberDatePickerState(),
        colors = DatePickerDefaults.colors(
            containerColor = Gray80,
            weekdayContentColor = Orange80,
            titleContentColor = Orange80,
            headlineContentColor = Orange80,
            subheadContentColor = Orange80,
            navigationContentColor = Orange80,
            dayContentColor = Orange80,
            selectedDayContainerColor = Orange80,
            selectedDayContentColor = Gray80,
            todayDateBorderColor = Orange80,
            todayContentColor = Orange80,
            dividerColor = Orange80,
        )
    )
}

//@Preview
@Composable
fun DayCellPreview() {
    //DayCell(day = 1, isCurrentMonth = true, date = Date(), selectedDate = mutableStateOf(null))
}

@Preview(showBackground = false)
@Composable
fun GridCalendarPreview() {
    GridCalendar()
}

@Preview(showBackground = true)
@Composable
fun PickerPreview() {
    UrkraftDatePicker()
}