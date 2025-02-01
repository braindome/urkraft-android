package se.braindome.urkraft.ui.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier.padding(2.dp)
            .background(color = if (isSelected) Color.Black else Color.Gray, shape = CircleShape)
            .size(15.dp)
    ) {}
}