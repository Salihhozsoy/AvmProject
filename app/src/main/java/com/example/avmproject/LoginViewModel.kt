package com.example.avmproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    var loginState: StateFlow<LoginState> = _loginState
    private val _message: MutableSharedFlow<String> = MutableSharedFlow()
    var message: SharedFlow<String> = _message



    fun login(emailText: String, passwordText: String) {
        viewModelScope.launch {
            if (!emailText.isNullOrEmpty() && !passwordText.isNullOrEmpty()) {
                getLogin(emailText,passwordText)
            } else {
                _message.emit("lütfen boş alan bırakmayınız")
            }
        }
    }

    private fun getLogin(emailText: String, passwordText: String) {
        viewModelScope.launch {

            Database.users.firstOrNull{it.email== emailText && it.password== passwordText}?.let {
                _loginState.value =LoginState.Success
            }?:kotlin.run {
                _loginState.value =LoginState.Error
            }
        }
    }
}