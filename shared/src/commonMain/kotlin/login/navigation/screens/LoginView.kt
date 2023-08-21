package login.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.launch
import login.navigation.components.LoginComponent
import login.navigation.components.RootComponent

@Composable
fun LoginView(component: LoginComponent) {
    MaterialTheme {
        val rootComponent: RootComponent
        val successLoginScreen: RootComponent.Child.SuccessLoginChild
        val model by component.model.subscribeAsState()
        var userAuthState = remember { model.userAuthState.value }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Friend Company",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                OutlinedTextField(
                    value = userAuthState.username,
                    onValueChange = { newUsername ->
                        userAuthState.username = newUsername
                    },
                    label = { Text("Username") },
                    singleLine = true,
                    isError = userAuthState.usernameError,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = userAuthState.password,
                    onValueChange = { newPassword -> userAuthState.password = newPassword },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = userAuthState.passwordError,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Switch(
                        checked = userAuthState.rememberMe,
                        onCheckedChange = { rememeberMe -> userAuthState.rememberMe = rememeberMe },
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text("Remember me")
                }

                Button(
                    onClick = {
                        userAuthState.usernameError = userAuthState.username.isBlank()
                        userAuthState.passwordError = userAuthState.password.isBlank()
                        coroutineScope.launch {
                            val userAuth =
                                userAuthState.login(userAuthState.username, userAuthState.password)
                            if (userAuth) {
                                component.onLoginSuccess()
                            } else {
                                userAuthState.usernameError = true
                                userAuthState.passwordError = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Log In")
                }

                if (userAuthState.usernameError || userAuthState.passwordError) {
                    Text(
                        text = "Invalid Credentials, try again",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colors.error
                    )
                }

                Text(
                    text = "Forgot password",
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
