package login.navigation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

// Root
interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    // Defines all possible child components
    sealed class Child {
        data class LoginChild(val component: LoginComponent) : Child()
        data class SuccessLoginChild(val component: SuccessLoginComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    @Parcelize
    private sealed interface Config : Parcelable {
        object Login:Config
        object Success:Config
    }

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Login, // The initial child component is Login Screen
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Login -> RootComponent.Child.LoginChild(loginComponent(componentContext))
            is Config.Success -> RootComponent.Child.SuccessLoginChild(successComponent(componentContext))
        }
    private fun loginComponent(componentContext: ComponentContext): LoginComponent =
        DefaultLoginComponent(
            componentContext = componentContext,
            onLoginComplete = { navigation.bringToFront(Config.Success) // Push the success component
            }

        )
    private fun successComponent(componentContext: ComponentContext): SuccessLoginComponent =
        DefaultSuccessLoginComponent(componentContext = componentContext)

    fun onBackClicked(toIndex: Int) {
            navigation.popTo(index = toIndex)
        }


}





//    private fun listComponent(componentContext: ComponentContext): ListComponent =
//        DefaultListComponent(
//            componentContext = componentContext,
//            onItemSelected = { item: String -> // Supply dependencies and callbacks
//                navigation.push(Config.Details(item = item)) // Push the details component
//            },
//        )
//
//    private fun detailsComponent(componentContext: ComponentContext, config: Config.Details): DetailsComponent =
//        DefaultDetailsComponent(
//            componentContext = componentContext,
//            item = config.item, // Supply arguments from the configuration
//            onFinished = navigation::pop, // Pop the details component
//        )



