package se.braindome.urkraft.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40

@Composable
fun ExerciseRow() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Surface(
                modifier = Modifier
                    .background(Color.Red)
                    .size(56.dp)
                    .clickable {
                        //showColorPicker = true
                    },
                color = Color.Red,
            ) { }
            TextField(
                value = "Bench press",
                onValueChange = {  },
                label = { Text("Exercise name") },
                placeholder = { Text("Enter exercise name") },
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
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}

@Preview
@Composable
fun ExerciseRowPreview() {
    ExerciseRow()
}
