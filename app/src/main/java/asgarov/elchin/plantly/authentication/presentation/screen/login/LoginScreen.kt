package asgarov.elchin.plantly.authentication.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import asgarov.elchin.plantly.authentication.presentation.screen.AuthViewModel
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import asgarov.elchin.plantly.core.navigation.NavigationRoute


@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val loginState by viewModel.loginState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(loginState.tokens) {
        loginState.tokens?.let {
            navController.navigate(NavigationRoute.ReminderRoute.route) {
                popUpTo(NavigationRoute.LoginRoute.route) { inclusive = true }
            }
        }
    }

    LoginContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            viewModel.login(email, password)
        },
        onNavigateToForgotPassword = {
            navController.navigate(NavigationRoute.ForgotPasswordRoute.route)
        },
        onNavigateToRegister = {
            navController.navigate(NavigationRoute.RegisterRoute.route)
        },
        isLoading = loginState.isLoading,
        errorMessage = loginState.error
    )
}
