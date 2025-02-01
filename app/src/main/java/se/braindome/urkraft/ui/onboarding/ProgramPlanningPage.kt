package se.braindome.urkraft.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.ui.theme.Typography
import timber.log.Timber

@Composable
fun ProgramPlanningPage() {
    Timber.tag("ProgramPlanningPage").d("Composable loaded")
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        items(4) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Week nr", style = Typography.titleLarge)
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.Black
                )
                OnboardingWeekday("Monday")
                OnboardingWeekday("Tuesday")
                OnboardingWeekday("Wednesday")
                OnboardingWeekday("Thursday")
                OnboardingWeekday("Friday")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF555555)
@Composable
fun ProgramPlanningPagePreview() {
    ProgramPlanningPage()
}