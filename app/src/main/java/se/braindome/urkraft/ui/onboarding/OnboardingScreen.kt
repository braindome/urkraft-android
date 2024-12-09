package se.braindome.urkraft.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.ui.components.TextButton

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
                            text = "Second page"
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

@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier.padding(2.dp)
            .background(color = if (isSelected) Color.Black else Color.Gray, shape = CircleShape)
            .size(15.dp)
    ) {

    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}

