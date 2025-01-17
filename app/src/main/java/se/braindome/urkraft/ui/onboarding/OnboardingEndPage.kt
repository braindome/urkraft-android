package se.braindome.urkraft.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.ui.theme.Typography
import se.braindome.urkraft.ui.components.TextButton

@Composable
fun OnboardingEndPage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        Text(text = "Save program and go back to home screen?", style = Typography.titleLarge)
        Spacer(modifier = Modifier.height(30.dp))
        TextButton(onClick = { /* TODO: Navigate to home screen */ }, label = "Confirm")
    }
}