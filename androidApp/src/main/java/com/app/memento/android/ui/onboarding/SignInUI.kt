package com.app.memento.android.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.memento.android.ui.common.EndTextNavBar
import com.app.memento.android.ui.common.LargeMementoButton
import com.app.memento.android.ui.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun SignInUI(
    navHostController: NavHostController,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    var firstNameText by rememberSaveable { mutableStateOf("") }
    var lastNameText by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            EndTextNavBar(endText = "Memento")
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Enter your name", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Enter your name to get started",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "First name",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstNameText,
                shape = RoundedCornerShape(12.dp),
                supportingText = { Text(text = signInViewModel.firstNameError.value ?: "") },
                isError = signInViewModel.firstNameError.value != null,
                onValueChange = { value ->
                    firstNameText = value
                    signInViewModel.validateFirstName(firstNameText)
                })

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Last name",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastNameText,
                shape = RoundedCornerShape(12.dp),
                supportingText = { Text(text = signInViewModel.lastNameError.value ?: "") },
                isError = signInViewModel.lastNameError.value != null,
                onValueChange = { value ->
                    lastNameText = value
                    signInViewModel.validateLastName(lastNameText)
                })
        }
        LargeMementoButton(
            onClick = { signInViewModel.saveSignInState(firstNameText, lastNameText)
                      navHostController.popBackStack()
                      navHostController.navigate(Screen.HomeScreen.route)},
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text(text = "Continue", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun SignInUIPreview() {
    SignInUI(navHostController = rememberNavController())
}