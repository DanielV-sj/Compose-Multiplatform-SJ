package com.myapplication.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(username:String, password:String): ViewModel() {
    private var _isLogin = MutableStateFlow(login(username, password))
    val isLogin = _isLogin.asStateFlow()
    private fun login(username: String, password: String): Boolean {
        return username == "a" && password == "a"
    }
}