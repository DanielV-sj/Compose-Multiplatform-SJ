package login
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

actual open class LoginViewModel actual constructor() {
    actual val userAuthState by mutableStateOf(UserAuthState())
    actual fun login(username: String, password: String): Boolean {
        val lowercaseUsername = username.lowercase()
        val lowercasePassword = password.lowercase()
        return lowercaseUsername == "a" && lowercasePassword == "a"
    }
}