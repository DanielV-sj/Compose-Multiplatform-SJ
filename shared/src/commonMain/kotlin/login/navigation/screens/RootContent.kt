package login.navigation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import login.navigation.components.RootComponent


@Composable
fun RootContent(rootComponent: RootComponent, modifier: Modifier = Modifier) {
    Children(stack= rootComponent.stack, animation = stackAnimation(fade())) {
        when (val child = it.instance) {
            is RootComponent.Child.LoginChild -> LoginView(child.component)
            is RootComponent.Child.SuccessLoginChild -> SuccessLoginView(child.component)
        }
    }
}

