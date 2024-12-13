package se.braindome.urkraft.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.components.TextButton
import se.braindome.urkraft.ui.components.UrkraftDropDownMenu
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80

@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(0, 0F, { 4 })
    var text = remember {""}
    Box(modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(state = pagerState) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    when (pagerState.currentPage) {
                        0 -> {
                            ProgramSelectionPage()
                        }
                        1 -> {
                            ProgramPropertyPage()
                        }
                        2 -> {
                            text = "Third page"
                        }
                        3 -> {
                            text = "Fourth page"
                        }
                    }
                    Text(text = text)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                repeat(4) {
                    CustomIndicator(isSelected= pagerState.currentPage == it)
                }
            }
        }
    }
}

@Composable
fun ProgramSelectionPage() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Text(text = "No programs found.", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(60.dp))
        Text("Create a new program",fontSize = 25.sp)
        Spacer(modifier = Modifier.height(30.dp))
        TextButton(onClick = { /* TODO: Navigate to create program screen */ }, label = "Start")
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(30.dp))
        Text("Select an existing program", fontSize = 25.sp)
        Text("Dropdown")
        Spacer(modifier = Modifier.height(100.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramPropertyPage() {
    val programName by rememberSaveable { mutableStateOf("") }
    val weekDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val switchStates = rememberSaveable { mutableStateOf(weekDays.associateWith { false }) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Text("Name your program", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = programName,
            onValueChange = {  },
            label = { Text("Exercise name") },
            placeholder = { Text("Enter exercise name") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Gray20,
                unfocusedContainerColor = Gray20,
                disabledContainerColor = Gray40,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black

            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(30.dp))
        Text("Number of weeks in a cycle", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(30.dp))
        UrkraftDropDownMenu(listOf("1", "2", "3", "4", "5", "6", "7", "8"))
        Spacer(modifier = Modifier.height(30.dp))
        Text("Select training days", fontSize = 30.sp)
        LazyColumn {
            items(weekDays) { day ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = day,
                        fontSize = 25.sp,
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Switch(
                        checked = switchStates.value[day] ?: false,
                        onCheckedChange = { isChecked ->
                            switchStates.value = switchStates.value.toMutableMap().apply { put(day, isChecked) }
                        },
                        thumbContent = { Text(if (switchStates.value[day] == true) "✓" else "") },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Gray80,
                            checkedThumbColor = Orange80,
                            uncheckedThumbColor = Gray40,
                            uncheckedTrackColor = Gray80
                        )
                    )
                }
            }
        }

    }
}

@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier.padding(2.dp)
            .background(color = if (isSelected) Color.Black else Color.Gray, shape = CircleShape)
            .size(15.dp)
    ) {

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF555555)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}

@Preview(showBackground = true, backgroundColor = 0xFF555555)
@Composable
fun ProgramNamePagePreview() {
    ProgramPropertyPage()
}

