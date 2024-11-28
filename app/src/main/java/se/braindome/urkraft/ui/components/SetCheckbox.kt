package se.braindome.urkraft.ui.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import se.braindome.urkraft.ui.theme.Gray10
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80

@Composable
fun SetCheckbox(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    //var checked by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = checked,
        onCheckedChange = { onCheckedChange(it) },
        colors = CheckboxDefaults.colors(
            checkedColor = Orange80,
            uncheckedColor = Gray80,
            checkmarkColor = Color.Black
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF2A2A2A)
@Composable
fun SetCheckboxPreview() {
    SetCheckbox(
        checked = TODO(),
        onCheckedChange = TODO()
    )
}