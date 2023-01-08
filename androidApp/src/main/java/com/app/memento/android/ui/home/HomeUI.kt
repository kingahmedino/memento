package com.app.memento.android.ui.home

import android.view.WindowManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.memento.android.R
import com.app.memento.android.ui.common.ReminderItem
import com.app.memento.android.ui.navigation.Screen
import com.app.memento.android.utils.getActivity

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun HomeUI(navHostController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val activity = LocalContext.current.getActivity()
    DisposableEffect(key1 = true) {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        onDispose {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }
    var searchText by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(bottom = 72.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Hi, ${homeViewModel.firstName.value}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "What plans do you have today?",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        modifier = Modifier.size(64.dp),
                        contentDescription = "avatar"
                    )
                }


                TextField(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    value = searchText,
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(
                            text = "Search for reminders",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus() //search for the reminder
                        }),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Back button"
                        )
                    },
                    onValueChange = { value ->
                        searchText = value
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.outline.copy(
                            alpha = 0.1f
                        ),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(20.dp)
            ) {

                if (homeViewModel.triggeredReminders.value.isEmpty() && homeViewModel.notYetTriggeredReminders.value.isEmpty()) {
                    item {
                        EmptyReminderUI(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                        )
                    }
                }

                if (homeViewModel.triggeredReminders.value.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = "Triggered Reminders⚡️",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.background)
                                .padding(vertical = 15.dp)
                        )
                    }

                    items(
                        homeViewModel.triggeredReminders.value,
                        key = { reminder -> "triggered_${reminder.id!!}" }) { reminder ->
                        ReminderItem(
                            reminder = reminder,
                            onClick = {})
                    }

                    item { Spacer(modifier = Modifier.height(20.dp)) }
                }

                if (homeViewModel.notYetTriggeredReminders.value.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = "Not yet Triggered😬️",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.background)
                                .padding(vertical = 15.dp)
                        )
                    }

                    items(
                        homeViewModel.notYetTriggeredReminders.value,
                        key = { reminder -> "notYetTriggered_${reminder.id!!}" }
                    ) { reminder ->
                        ReminderItem(
                            modifier = Modifier.fillMaxSize(),
                            reminder = reminder,
                            onClick = {})
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter), contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(color = MaterialTheme.colorScheme.background)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { }) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .aspectRatio(1f)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "Home",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        navHostController.navigate(Screen.ProfileScreen.route)
                    }) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .aspectRatio(1f)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile_avatar),
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.ReminderDetailsScreen.route)
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "floating action button"
                )
            }
        }
    }
}

@Composable
fun EmptyReminderUI(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.work_illustration),
            contentDescription = "No reminders image"
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "No reminders", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Create a reminder and it will show up here",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.outline),
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun HomeUIPreview() {
    HomeUI(navHostController = rememberNavController())
}

@Preview
@Composable
fun EmptyReminderPagePreview() {
    EmptyReminderUI(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    )
}
