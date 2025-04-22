package asgarov.elchin.plantly.authentication.presentation.screen.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.authentication.presentation.screen.AuthViewModel
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun LoginScreen(navController: NavController) {
    val parentEntry = remember { navController.getBackStackEntry("authentication_graph") }
    val viewModel: AuthViewModel = hiltViewModel(parentEntry)

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(loginState.tokens) {
        loginState.tokens?.let {
            Log.d("LoginNavigation", "Tokens exist, navigating to Reminder screen")
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
            Log.d("LoginUI", "Login button clicked with email: $email")
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
