package login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserAuthState
{
    var username: String by mutableStateOf("")
    var password : String by mutableStateOf("")
    var usernameError: Boolean by mutableStateOf(false)
    var passwordError: Boolean by mutableStateOf(false)
    var rememberMe: Boolean by mutableStateOf(false)
}
expect open class LoginViewModel() {
     val userAuthState: UserAuthState
    fun login(username: String, password: String): Boolean
}