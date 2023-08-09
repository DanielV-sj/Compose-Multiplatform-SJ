import androidx.compose.ui.window.ComposeUIViewController
import login.LoginView

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { LoginView() }