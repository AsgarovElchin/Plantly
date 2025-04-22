package asgarov.elchin.plantly.authentication.presentation.screen.register

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.authentication.presentation.screen.AuthViewModel
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val registerState by viewModel.registerState.collectAsState()

    val email = remember {
        navController.currentBackStackEntry
            ?.arguments?.getString("email") ?: ""
    }

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    LaunchedEffect(registerState.user) {
        if (registerState.user != null) {
            navController.navigate(NavigationRoute.LoginRoute.route) {
                popUpTo(NavigationRoute.RegisterRoute.route) { inclusive = true }
            }
        }
    }

    RegisterContent(
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onRegisterClick = {
            if (password == confirmPassword && password.isNotEmpty()) {
                viewModel.register(email, password)
            }
        },
        isLoading = registerState.isLoading,
        errorMessage = if (password != confirmPassword && confirmPassword.isNotEmpty())
            "Passwords do not match"
        else registerState.error,
        onNavigateToLogin = {
            navController.navigate(NavigationRoute.LoginRoute.route)
        }
    )
}