package se.braindome.urkraft.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import se.braindome.urkraft.ui.components.TextButton
import timber.log.Timber

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