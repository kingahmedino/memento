package com.app.memento.android.ui.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.memento.android.ui.navigation.Screen
import com.app.memento.android.utils.DataStoreUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val dataStoreUtils: DataStoreUtils): ViewModel() {
    var startDestination = mutableStateOf("")
    private set

    init {
        viewModelScope.launch {
            delay(500L)
            dataStoreUtils.readUsername().first { username ->
                if (username.isNotEmpty())
                    startDestination.value = Screen.HomeScreen.route
                else {
                    dataStoreUtils.readOnBoardingState().first { completed ->
                        if (completed)
                            startDestination.value = Screen.SignInScreen.route
                        else
                            startDestination.value = Screen.OnBoardingScreen.route
                        true
                    }
                }
                true
            }
        }
    }
}