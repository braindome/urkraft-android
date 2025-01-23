package se.braindome.urkraft.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray80

@Composable
fun UrkraftDropDownMenu(options: List<String>) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf(options.first()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = { expanded = !expanded },
            label = selectedOption
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Gray40,
            shape = RoundedCornerShape(8.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    leadingIcon = {
                        if (option == selectedOption) {
                            Text("✓")
                        }
                    },
                    colors = MenuItemColors(
                        textColor = Gray80,
                        leadingIconColor = Color.Black,
                        trailingIconColor = Color.Black,
                        disabledTextColor = Gray40,
                        disabledLeadingIconColor = Color.Black,
                        disabledTrailingIconColor = Color.Black
                    ),
                    modifier = Modifier.background(Gray40)
                )
            }
        }

    }
}

@Composable
fun TextDropDownMenu(options: List<String>, disabled: Boolean = false) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf(options.first()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(140.dp)
                .fillMaxHeight()
                .then(if (!disabled) Modifier.clickable { expanded = !expanded } else Modifier)
        ) {
            Text(
                text = selectedOption,
                fontSize = 18.sp,
                color = if (disabled) Gray40 else Gray20,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "Expand",
                tint = if (disabled) Gray40 else Gray20,
            )
        }

        if (!disabled) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = Gray40,
                shape = RoundedCornerShape(8.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        },
                        leadingIcon = {
                            if (option == selectedOption) {
                                Text("✓")
                            }
                        },
                        colors = MenuItemColors(
                            textColor = Gray80,
                            leadingIconColor = Color.Black,
                            trailingIconColor = Color.Black,
                            disabledTextColor = Gray40,
                            disabledLeadingIconColor = Color.Black,
                            disabledTrailingIconColor = Color.Black
                        ),
                        modifier = Modifier.background(Gray40)
                    )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun TextDropDownMenuPreview() {
    TextDropDownMenu(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview() {
    UrkraftDropDownMenu(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
}