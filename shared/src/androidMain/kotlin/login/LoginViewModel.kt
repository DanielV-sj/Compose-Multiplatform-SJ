package login
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*

actual open class LoginViewModel actual constructor(){
    actual var username by mutableStateOf("")
    actual var password by mutableStateOf("")
    actual var usernameError by mutableStateOf(false)
    actual var passwordError by mutableStateOf(false)
    actual var rememberMe by mutableStateOf(false)

    actual fun login(username: String, password: String): Boolean {
        val lowercaseUsername = username.lowercase()
        val lowercasePassword = password.lowercase()
        return lowercaseUsername == "a" && lowercasePassword == "a"
    }
}