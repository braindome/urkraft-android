package se.braindome.urkraft.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray60
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80

enum class CheckboxState {
    EMPTY, COMPLETED, FAILED
}

@Composable
fun SetCheckbox(state: CheckboxState, onStateChange: (CheckboxState) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val icon = when (state) {
        CheckboxState.EMPTY -> null
        CheckboxState.COMPLETED -> R.drawable.round_check_circle_24
        CheckboxState.FAILED -> R.drawable.baseline_cancel_24
    }

    Box(
        modifier = Modifier
            .size(22.dp)
            .background(
                color = Gray40,
                shape = CircleShape
            )
            .border(
                width = 1.dp,
                color = Gray80,
                shape = CircleShape
            )
            .clickable { expanded = !expanded }
    ) {
        icon?.let {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = when (state) {
                    CheckboxState.COMPLETED -> Color.Green
                    CheckboxState.FAILED -> Color.Red
                    else -> Color.Unspecified
                },
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.Center)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Gray60,
        ) {
            DropdownMenuItem(
                onClick = {
                    onStateChange(CheckboxState.EMPTY)
                    expanded = false
                },
                text = { Text("Empty") },

            )
            DropdownMenuItem(
                onClick = {
                    onStateChange(CheckboxState.COMPLETED)
                    expanded = false
                },
                text = { Text("Completed") },
                leadingIcon = { Icon(
                    painter = painterResource(id = R.drawable.round_check_circle_24),
                    contentDescription = null,
                    tint = Color.Green,
                )}
            )
            DropdownMenuItem(
                onClick = {
                    onStateChange(CheckboxState.FAILED)
                    expanded = false
                },
                text = { Text("Failed") },
                leadingIcon = { Icon(
                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                    contentDescription = null,
                    tint = Color.Red,
                )}
            )
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF2A2A2A)
@Composable
fun SetCheckboxPreview() {
    var state by rememberSaveable { mutableStateOf(CheckboxState.EMPTY) }

    SetCheckbox(
        state = state,
        onStateChange = { newState -> state = newState }
    )
}