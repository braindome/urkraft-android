package se.braindome.urkraft

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UrkraftApp() {
    WorkoutScreen()
}

@Preview(showBackground = true)
@Composable
fun UrkraftAppPreview() {
    UrkraftApp()
}