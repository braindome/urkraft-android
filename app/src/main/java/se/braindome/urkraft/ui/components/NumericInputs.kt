package se.braindome.urkraft.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.R
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40

enum class NumericInputType {
    SETS,
    REPS,
}

@Composable
fun NumericTextField(
    onValueChange: (String) -> Unit,
    value: String,
    inputType: NumericInputType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val numericRegex = when (inputType) {
        NumericInputType.SETS -> Regex("^$|^([1-9][0-9]{0,1}|20)$")
        NumericInputType.REPS -> Regex("^$|^([1-9][0-9]{0,1}|50)$")
    }

    TextField(
        value = value,
        enabled = enabled,
        onValueChange = {
            val newValue = it
            if (newValue.matches(numericRegex)) {
                onValueChange(newValue)
            }
        },
        label = { Text(when (inputType) {
            NumericInputType.SETS -> "Sets"
            NumericInputType.REPS -> "Reps"
        }) },
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
        trailingIcon = {
            Column {
                IconButton(
                    onClick = {
                        val currentValue = value.toIntOrNull() ?: 0
                        val increment = 1
                        val newValue = currentValue + increment
                        onValueChange(newValue.toString())
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_drop_up_24),
                        contentDescription = "Increase ${inputType.name}"
                    )
                }
                IconButton(
                    onClick = {
                        val currentValue = value.toIntOrNull() ?: 0
                        val decrement = 1
                        val newValue = (currentValue - decrement).coerceAtLeast(0)
                        onValueChange(newValue.toString())
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = "Decrease ${inputType.name}"
                    )
                }
            }
        },
        modifier = modifier
    )

}

@Composable
fun DecimalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Weight") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
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
        trailingIcon = {
            Column {
                IconButton(
                    onClick = {
                        val currentValue = value.replace(",", ".").toDoubleOrNull() ?: 0.0
                        val increment = 0.25
                        val newValue = currentValue + increment
                        onValueChange(newValue.toString())
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_drop_up_24),
                        contentDescription = "Increase weigh}"
                    )
                }
                IconButton(
                    onClick = {
                        val currentValue = value.replace(",", ".").toDoubleOrNull() ?: 0.0
                        val decrement = 0.25
                        val newValue = (currentValue - decrement).coerceAtLeast(0.0)
                        onValueChange(newValue.toString())
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = "Decrease weight"
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun IntegerTextFieldPreview() {
    NumericTextField(
        onValueChange = {},
        value = "",
        inputType = NumericInputType.SETS
    )
}
