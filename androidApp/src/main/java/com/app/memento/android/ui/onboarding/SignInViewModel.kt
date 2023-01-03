package com.app.memento.android.ui.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.memento.android.utils.DataStoreUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val dataStoreUtils: DataStoreUtils) :
    ViewModel() {

    private val _firstNameError: MutableState<String?> = mutableStateOf(null)
    val firstNameError: State<String?> = _firstNameError

    private val _lastNameError: MutableState<String?> = mutableStateOf(null)
    val lastNameError: State<String?> = _lastNameError

    private val nameRegex = Regex("^([a-zA-Z]{2,}[\\s]?){1,3}\$")

    fun validateFirstName(text: String): Boolean {
        var isValid = true

        if (text.isEmpty()) {
            _firstNameError.value = "First name cannot be empty"
            isValid = false
        } else if (text.length < 3) {
            _firstNameError.value = "First name must be more than 2 characters"
            isValid = false
        } else if (!nameRegex.matches(text)) {
            _firstNameError.value = "Value is invalid for first name"
            isValid = false
        } else
            _firstNameError.value = null

        return isValid
    }

    fun validateLastName(text: String): Boolean {
        var isValid = true

        if (text.isEmpty()) {
            _lastNameError.value = "Last name cannot be empty"
            isValid = false
        } else if (text.length < 3) {
            _lastNameError.value = "Last name must be more than 2 characters"
            isValid = false
        } else if (!nameRegex.matches(text)) {
            _lastNameError.value = "Value is invalid for last name"
            isValid = false
        } else
            _lastNameError.value = null

        return isValid
    }

    fun saveSignInState(firstName: String, lastName: String) {
        if (validateFirstName(firstName) && validateLastName(lastName))
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreUtils.saveSignInState(name = "$firstName $lastName")
            }
    }
}