package se.braindome.urkraft.ui.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.components.TextButton
import se.braindome.urkraft.ui.components.UrkraftDropDownMenu
import se.braindome.urkraft.ui.components.UrkraftTextField
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80
import se.braindome.urkraft.ui.theme.Typography
import timber.log.Timber

@Composable
fun OnboardingScreen() {
    //Timber.tag("OnboardingScreen").d("Composable loaded")
    val pagerState = rememberPagerState(0, 0F, { 4 })
    val coroutineScope = rememberCoroutineScope()
    var text = remember {""}
    Box(modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            AnimatedVisibility(
                visible = pagerState.currentPage == page,
                enter = slideInHorizontally { width -> width },
                exit = slideOutHorizontally { width -> -width }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        when (pagerState.currentPage) {
                            0 -> {
                                ProgramSelectionPage(pagerState, coroutineScope)
                            }
                            1 -> {
                                ProgramPropertyPage()
                            }
                            2 -> {
                                ProgramPlanningPage()
                            }
                            3 -> {
                                OnboardingEndPage()
                            }
                        }
                        Text(text = text)
                    }
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
fun ProgramSelectionPage(pagerState: PagerState, coroutineScope: CoroutineScope) {
    Timber.tag("ProgramSelectionPage").d("Composable loaded")
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
        TextButton(
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(1)
                }
            },
            label = "Start"
        )
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(30.dp))
        Text("Select an existing program", fontSize = 25.sp)
        Text("Dropdown")
        Spacer(modifier = Modifier.height(100.dp))

    }
}

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
fun ProgramPropertyPagePreview() {
    ProgramPropertyPage()
}

@Preview(showBackground = true, backgroundColor = 0xFF555555)
@Composable
fun ProgramPlanningPagePreview() {
    ProgramPlanningPage()
}