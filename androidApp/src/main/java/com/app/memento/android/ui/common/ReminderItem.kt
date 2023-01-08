package com.app.memento.android.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.memento.android.R
import com.app.memento.domain.reminder.Reminder
import com.app.memento.domain.time.DateTimeUtil

@ExperimentalMaterial3Api
@Composable
fun ReminderItem(modifier: Modifier = Modifier, reminder: Reminder, onClick: (Reminder) -> Unit) {
    Card(modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        onClick = {onClick(reminder)},
        colors = CardDefaults.cardColors(
            containerColor = Color(reminder.colorHex)
        )
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = reminder.title, style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black))
                if (reminder.triggered)
                    Icon(
                        painter = painterResource(id = R.drawable.tick_icon),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Check mark for triggered reminder"
                    )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = reminder.description,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location_icon),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Location icon"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = reminder.location, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black))
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ReminderItemPreview() {
    ReminderItem(modifier = Modifier.fillMaxWidth(), Reminder(
        id = null,
        title = "Buy water",
        description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
        location = "Sanrab, Tanke, Oke-Odo",
        latitude = 8.32333,
        longitude = 4.54343,
        colorHex = 0xFFFFFBE5,
        created = DateTimeUtil.now(),
        triggered = false
    ), onClick = {})
}