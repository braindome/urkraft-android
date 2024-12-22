package se.braindome.urkraft.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.braindome.urkraft.ui.theme.Gray20
import se.braindome.urkraft.ui.theme.Gray40
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.Orange80

@Composable
fun TextButton(onClick: () -> Unit, label: String) {
    Button(
        onClick =  onClick,
        colors = ButtonColors(
            containerColor = Orange80,
            contentColor = Gray80,
            disabledContentColor = Gray20,
            disabledContainerColor = Gray40
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {
        Text(label, fontSize = 18.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2A2A2A)
@Composable
fun TextButtonPreview() {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        TextButton(onClick = {},label ="Click")
    }
}