import androidx.compose.runtime.Composable
import login.LoginView

actual fun getPlatformName(): String = "Android"

//@Composable fun MainView() = App()
@Composable fun MainView() = LoginView()
