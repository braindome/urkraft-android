package se.braindome.urkraft.ui.components

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UrkraftSwitch(
    onValueChange: (String) -> Unit,
    value: String,
) {
}

@Preview(showBackground = true)
@Composable
fun UrkraftSwitchPreview() {
    UrkraftSwitch({}, "Test")
}