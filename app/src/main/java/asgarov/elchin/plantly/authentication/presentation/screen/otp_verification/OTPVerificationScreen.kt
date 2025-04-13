package asgarov.elchin.plantly.authentication.presentation.screen.otp_verification

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import asgarov.elchin.plantly.authentication.presentation.screen.AuthViewModel
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun OTPVerificationScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val otpState by viewModel.otpVerifyState.collectAsState()

    var otp by remember { mutableStateOf("") }

    val email = remember {
        navController.currentBackStackEntry?.arguments?.getString("email") ?: ""
    }
    val type = remember {
        navController.currentBackStackEntry?.arguments?.getString("type") ?: "PASSWORD_RESET"
    }

    LaunchedEffect(otpState.isSuccess) {
        if (otpState.isSuccess) {
            if (type == "PASSWORD_RESET") {
                navController.navigate(NavigationRoute.CreateNewPassword.createRoute(email)) {
                    popUpTo(NavigationRoute.OTPVerificationRoute.route) { inclusive = true }
                }
            } else if (type == "REGISTRATION") {
                navController.navigate(NavigationRoute.RegisterRoute.createRoute(email)) {
                    popUpTo(NavigationRoute.OTPVerificationRoute.route) { inclusive = true }
                }
            }
        }
    }
    OTPVerificationContent(
        otp = otp,
        onOtpChange = { otp = it },
        onVerifyClick = {
            viewModel.verifyOtp(email, otp, type)
        },
        onResendClick = {
            viewModel.sendOtp(email, type)
        },
        isLoading = otpState.isLoading,
        errorMessage = otpState.error
    )
}
