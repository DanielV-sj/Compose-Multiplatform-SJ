import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import login.LoginView

actual fun getPlatformName(): String = "Android"
@Composable fun MainView() = Navigator(LoginView())