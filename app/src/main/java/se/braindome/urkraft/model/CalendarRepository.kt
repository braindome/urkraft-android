package se.braindome.urkraft.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.temporal.WeekFields
import java.util.Calendar
import java.util.Date
import java.util.Locale

object CalenderRepository {
    // A helper data class to represent a week
    data class Week(val weekNumber: Int, val dates: List<DateInfo>)

    // A helper data class to represent a date
    data class DateInfo(val dayOfMonth: Int, val isCurrentMonth: Boolean, val date: Date)

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateWeeksForMonth(date: Date): List<Week> {
        val localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        val firstDayOfMonth = localDate.withDayOfMonth(1)
        val lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth())

        val weekFields = WeekFields.of(Locale.FRANCE) // Use a locale where Monday is the first day of the week

        val weeks = mutableListOf<Week>()
        var currentWeek = mutableListOf<DateInfo>()
        var currentWeekNumber = firstDayOfMonth.get(weekFields.weekOfWeekBasedYear())

        var currentDate = firstDayOfMonth.minusDays(firstDayOfMonth.dayOfWeek.value.toLong() - 1) // Start from Monday of the first week

        while (currentDate <= lastDayOfMonth || currentDate.dayOfWeek != java.time.DayOfWeek.MONDAY) {
            if (currentDate.get(weekFields.weekOfWeekBasedYear()) != currentWeekNumber) {
                weeks.add(Week(currentWeekNumber, currentWeek))
                currentWeek = mutableListOf()
                currentWeekNumber = currentDate.get(weekFields.weekOfWeekBasedYear())
            }

            currentWeek.add(DateInfo(currentDate.dayOfMonth, currentDate.month == localDate.month, Date.from(currentDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())))
            currentDate = currentDate.plusDays(1)
        }

        if (currentWeek.isNotEmpty()) {
            weeks.add(Week(currentWeekNumber, currentWeek))
        }

        // Ensure there are always six weeks
        while (weeks.size < 6) {
            currentWeek = mutableListOf()
            for (i in 1..7) {
                currentWeek.add(DateInfo(currentDate.dayOfMonth, false, Date.from(currentDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())))
                currentDate = currentDate.plusDays(1)
            }
            weeks.add(Week(currentWeekNumber, currentWeek))
            currentWeekNumber = currentDate.get(weekFields.weekOfWeekBasedYear())
        }

        return weeks
    }


    // Generate days for a given month
    fun generateDaysForMonth(date: Date): List<DateInfo> {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val days = mutableListOf<DateInfo>()
        while (calendar.get(Calendar.MONTH) == date.month) {
            days.add(DateInfo(calendar.get(Calendar.DAY_OF_MONTH), true, calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return days
    }
}