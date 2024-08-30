package se.braindome.urkraft.ui.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeeklyCard() {
    val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")
    ElevatedCard(
        colors = CardColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp),

            ) {
            weekDays.forEach { day ->
                Column(
                    modifier = Modifier
                        .height(64.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = day,
                            modifier = Modifier.padding(start = 8.dp).size(24.dp)
                        )
                        WeeklyCardItem()
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .height(1.dp)
                            .padding(
                                top = 16.dp,
                                start = 32.dp,
                                end = 16.dp
                            )
                    )
                }

            }
        }
    }
}

@Composable
fun WeeklyCardItem() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.background(Color.Yellow)
            .height(56.dp)
            .fillMaxWidth(),

    ) {
        Text(
            lineHeight = 32.sp,
            text = "This is a workout",
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(15.dp))
                .background(Color.Cyan)
                .fillMaxWidth()
                .height(32.dp)
                .padding(start = 8.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyCardPreview() {
    WeeklyCard()
}