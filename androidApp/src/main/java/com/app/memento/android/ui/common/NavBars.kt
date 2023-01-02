package com.app.memento.android.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.memento.android.R

@Composable
fun EndTextNavBar(
    screenTitle: String = "",
    endText: String,
    onEndTextClicked: () -> Unit = {},
    canNavigateBack: Boolean = false,
    onBackIconClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (canNavigateBack)
            OutlinedButton(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(width = 40.dp, height = 40.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = onBackIconClicked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Back button"
                )
            }
        if (screenTitle.isNotEmpty())
            Text(text = screenTitle, style = MaterialTheme.typography.headlineSmall)

        Text(text = endText, style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary), modifier = Modifier.clickable { onEndTextClicked() })
    }
}

@Composable
fun EndIconNavBar(
    screenTitle: String = "",
    canNavigateBack: Boolean = false,
    onBackIconClicked: () -> Unit = {},
    endIcon: Painter,
    onEndIconClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (canNavigateBack)
            OutlinedButton(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(width = 40.dp, height = 40.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = onBackIconClicked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Back button"
                )
            }
        if (screenTitle.isNotEmpty())
            Text(text = screenTitle, style = MaterialTheme.typography.headlineSmall)

        OutlinedButton(
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .padding(end = 10.dp)
                .size(width = 40.dp, height = 40.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = onEndIconClicked
        ) {
            Icon(
                painter = endIcon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Back button"
            )
        }
    }
}

@Composable
@Preview
fun EndTextNavBarPreview() {
    Surface(color = Color.White) {
        EndTextNavBar(screenTitle = "Personal Info", endText = "Skip", canNavigateBack = true, onBackIconClicked = {})
    }
}

@Composable
@Preview
fun EndIconNavBarPreview() {
    Surface(color = Color.White) {
        EndIconNavBar(screenTitle = "Check your name", endIcon = painterResource(id = R.drawable.search_icon), canNavigateBack = true)
    }
}