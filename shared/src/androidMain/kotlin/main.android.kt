import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import login.navigation.components.DefaultRootComponent
import login.navigation.screens.RootContent

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(root: DefaultRootComponent) =
    RootContent(rootComponent = root, modifier = Modifier.fillMaxSize())