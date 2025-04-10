package asgarov.elchin.plantly.authentication.presentation.screen.create_new_password

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import asgarov.elchin.plantly.authentication.presentation.screen.AuthViewModel
import asgarov.elchin.plantly.core.navigation.NavigationRoute


@Composable
fun CreateNewPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val resetPasswordState by viewModel.resetPasswordState.collectAsState()

    val navBackStackEntry = remember(navController) {
        navController.currentBackStackEntry
    }

    val email = navBackStackEntry?.arguments?.getString("email").orEmpty()

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    LaunchedEffect(resetPasswordState.passwordReset) {
        if (resetPasswordState.passwordReset) {
            navController.navigate(NavigationRoute.PasswordChanged.route) {
                popUpTo(NavigationRoute.CreateNewPassword.route) { inclusive = true }
            }
        }
    }

    CreateNewPasswordContent(
        newPassword = newPassword,
        confirmPassword = confirmPassword,
        onNewPasswordChange = { newPassword = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onResetPasswordClick = {
            if (newPassword == confirmPassword && newPassword.isNotEmpty()) {
                viewModel.resetPassword(email, newPassword)
            }
        },
        isLoading = resetPasswordState.isLoading,
        errorMessage = if (newPassword != confirmPassword && confirmPassword.isNotEmpty())
            "Passwords do not match"
        else resetPasswordState.error
    )
}