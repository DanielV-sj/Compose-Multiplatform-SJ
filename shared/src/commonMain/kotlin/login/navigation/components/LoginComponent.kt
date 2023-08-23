package login.navigation.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value



class UserAuthState {
    var username: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var usernameError: Boolean by mutableStateOf(false)
    var passwordError: Boolean by mutableStateOf(false)
    var rememberMe: Boolean by mutableStateOf(false)
    fun login(username: String, password: String): Boolean {
        val lowercaseUsername = username.lowercase()
        val lowercasePassword = password.lowercase()
        return lowercaseUsername == "a" && lowercasePassword == "a"
    }
}

interface LoginComponent {
    val model: Value<Model>
    fun onLoginSuccess()
    data class Model(
        val userAuthState: MutableState<UserAuthState>,
    )
}

class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val onLoginComplete: (login:Boolean) -> Unit
) : LoginComponent{
    override val model: Value<LoginComponent.Model> =
        MutableValue(LoginComponent.Model(mutableStateOf(UserAuthState())))
    override fun onLoginSuccess() {
        onLoginComplete(true)
        println("Login Success")
    }
}

