package login

expect open class LoginViewModel() {
    var username: String
    var password: String
    var usernameError: Boolean
    var passwordError: Boolean
    var rememberMe: Boolean
    fun login(username: String, password: String): Boolean
}