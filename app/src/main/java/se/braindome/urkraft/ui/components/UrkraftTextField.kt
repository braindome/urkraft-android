package se.braindome.urkraft.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40

@Composable
fun UrkraftTextField(
    onValueChange: (String) -> Unit,
    value: String,
    label: String,
) {
    TextField(
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
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
}