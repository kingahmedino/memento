package com.app.memento.android.ui.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.app.memento.android.R
import com.app.memento.domain.reminder.Reminder
import com.app.memento.domain.time.DateTimeUtil
import kotlin.math.roundToInt

@SuppressLint("UnusedTransitionTargetStateParameter")
@ExperimentalMaterial3Api
@Composable
fun ReminderItem(
    modifier: Modifier = Modifier,
    reminder: Reminder,
    onClick: (Reminder) -> Unit
) {
    val boxColor = remember { mutableStateOf(Color.Transparent) }
    val leftColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    val rightColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)

    val expandedState = remember { mutableStateOf(ExpandState.COLLAPSED) }

    val offsetAnimatable by animateFloatAsState(
        targetValue =
        when (expandedState.value) {
            ExpandState.LEFT_EXPANDED -> 160f
            ExpandState.RIGHT_EXPANDED -> -160f
            else -> 0f
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(color = boxColor.value),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart),
            onClick = {}) {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(id = R.drawable.pin_icon),
                contentDescription = "Pin icon"
            )
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = {}) {
            Icon(
                tint = MaterialTheme.colorScheme.error,
                painter = painterResource(id = R.drawable.trash_icon),
                contentDescription = "Pin icon"
            )
        }


        Card(
            modifier = modifier
                .offset { IntOffset(offsetAnimatable.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        when {
                            dragAmount >= 30 -> {
                                if (expandedState.value == ExpandState.RIGHT_EXPANDED)
                                    expandedState.value = ExpandState.COLLAPSED
                                else if (expandedState.value == ExpandState.COLLAPSED) {
                                    expandedState.value = ExpandState.LEFT_EXPANDED
                                    boxColor.value = leftColor
                                }
                            }
                            dragAmount < -30 -> {
                                if (expandedState.value == ExpandState.LEFT_EXPANDED)
                                    expandedState.value = ExpandState.COLLAPSED
                                else if (expandedState.value == ExpandState.COLLAPSED) {
                                    expandedState.value = ExpandState.RIGHT_EXPANDED
                                    boxColor.value = rightColor
                                }
                            }
                        }
                    }
                },
            shape = RoundedCornerShape(18.dp),
            onClick = { onClick(reminder) },
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
                    Text(
                        text = reminder.title,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                    )
                    Row {
                        if (reminder.triggered)
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(id = R.drawable.tick_icon),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Check mark for triggered reminder"
                            )
                        Spacer(modifier = Modifier.width(5.dp))
                        if (reminder.pinned)
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(id = R.drawable.pin_icon),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Pin icon"
                            )
                    }
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
                    Text(
                        text = reminder.location,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
                    )
                }
            }
        }
    }
}


enum class ExpandState {
    RIGHT_EXPANDED, COLLAPSED, LEFT_EXPANDED
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
        triggered = true,
        pinned = true
    ), onClick = {})
}
