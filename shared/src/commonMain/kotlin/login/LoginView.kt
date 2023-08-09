package login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch



@Composable
fun LoginView() {
    MaterialTheme {
        val viewModel = LoginViewModel()
        var inputUsername by remember { mutableStateOf(viewModel.username) }
        var inputPassword by remember { mutableStateOf(viewModel.password) }
        var usernameError by remember { mutableStateOf(viewModel.usernameError) }
        var passwordError by remember { mutableStateOf(viewModel.passwordError) }
        var rememberMe by remember {mutableStateOf(viewModel.rememberMe) }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Welcome to Friend Company",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = inputUsername,
                    onValueChange = { inputUsername = it },
                    label = { Text("Username") },
                    singleLine = true,
                    isError = usernameError,
                    modifier = Modifier
                        .fillMaxWidth()

                )

                OutlinedTextField(
                    value = inputPassword,
                    onValueChange = { inputPassword = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = passwordError,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text("Remember me")
                }

                Button(
                    onClick = {
                        usernameError = inputUsername.isBlank()
                        passwordError = inputPassword.isBlank()
                        coroutineScope.launch {
                            val userAuth = viewModel.login(inputUsername, inputPassword)
                            if (userAuth) {
                                println("Login Success")
//                                navController.navigate(route = AppScreensRoute.Auth.route)
                            } else {
                                usernameError = true
                                passwordError = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Log In")
                }
                if (usernameError || passwordError) {
                    Text(
                        text = "Invalid Credentials, try again",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colors.error,

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
