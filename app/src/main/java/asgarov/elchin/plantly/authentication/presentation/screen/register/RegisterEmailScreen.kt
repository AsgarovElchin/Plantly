package asgarov.elchin.plantly.authentication.presentation.screen.register

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
fun RegisterEmailScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val otpSendState by viewModel.otpSendState.collectAsState()

    var email by remember { mutableStateOf("") }

    LaunchedEffect(otpSendState.isSuccess) {
        if (otpSendState.isSuccess) {
            navController.navigate(NavigationRoute.OTPVerificationRoute.createRoute(email, "REGISTRATION"))
        }
    }

    RegisterEmailContent(
        email = email,
        onEmailChange = { email = it },
        onSendCodeClick = {
            viewModel.sendOtp(email, "REGISTRATION")
        },
        onNavigateToLogin = {
            navController.navigate(NavigationRoute.LoginRoute.route)
        },
        isLoading = otpSendState.isLoading,
        errorMessage = otpSendState.error
    )
}
