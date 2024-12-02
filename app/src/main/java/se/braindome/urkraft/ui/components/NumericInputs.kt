package se.braindome.urkraft.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40

@Composable
fun SetsTextField(onValueChange: (String) -> Unit, value: String, modifier: Modifier = Modifier) {
    //val numericRegex = Regex("^$|^[1-9][0-9]?$|^20$")
    val numericRegex = Regex("^$|^([1-9][0-9]{0,1}|20)$")

    TextField(
        value = value,
        onValueChange = {
            val newValue = it
            if (newValue.matches(numericRegex)) {
                onValueChange(newValue)
            }
        },
        label = { Text("Sets") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
        //suffix = { Text(" kg") },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun IntegerTextFieldPreview() {
    SetsTextField(
        onValueChange = {},
        value = ""
    )
}