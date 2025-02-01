package se.braindome.urkraft.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.components.UrkraftDropDownMenu
import se.braindome.urkraft.ui.components.UrkraftTextField
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80
import se.braindome.urkraft.ui.theme.Typography
import timber.log.Timber

@Composable
fun ProgramPropertyPage() {
    Timber.tag("ProgramPropertyPage").d("Composable loaded")

    val programName by rememberSaveable { mutableStateOf("") }
    val weekDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val switchStates = rememberSaveable { mutableStateOf(weekDays.associateWith { false }) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text("Name your program", style = Typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))
        UrkraftTextField(
            value = programName,
            onValueChange = { /* TODO */ },
            label = "Program name",
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text("Number of weeks in a cycle", style = Typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))
        UrkraftDropDownMenu(listOf("1", "2", "3", "4", "5", "6", "7", "8"))
        Spacer(modifier = Modifier.height(20.dp))
        Text("Select training days", style = Typography.titleLarge)
        LazyColumn(
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            items(weekDays) { day ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = day,
                        style = Typography.titleMedium
                    )
                    Switch(
                        checked = switchStates.value[day] ?: false,
                        onCheckedChange = { isChecked ->
                            switchStates.value = switchStates.value.toMutableMap().apply { put(day, isChecked) }
                        },
                        thumbContent = {
                            Icon(
                                painter = painterResource(
                                    id = if (switchStates.value[day] == true) R.drawable.check_circle_outline_24
                                    else R.drawable.switch_unchecked_24),
                                tint = Color.Black,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Gray80,
                            checkedThumbColor = Orange80,
                            uncheckedThumbColor = Gray40,
                            uncheckedTrackColor = Gray80,
                            uncheckedBorderColor = Gray40,
                            checkedBorderColor= Gray40
                        ),
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF555555)
@Composable
fun ProgramPropertyPagePreview() {
    ProgramPropertyPage()
}