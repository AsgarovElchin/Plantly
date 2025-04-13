package asgarov.elchin.plantly.feature_profile.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.authentication.presentation.screen.AuthViewModel
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val logoutState by viewModel.logoutState.collectAsState()

    LaunchedEffect(logoutState.isLoggedOut) {
        if (logoutState.isLoggedOut) {
            navController.navigate(NavigationRoute.LoginRoute.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }


    Box {
        ProfileContent(
            onForgotPassword = {
                navController.navigate(NavigationRoute.ForgotPasswordRoute.route)
            },
            onLogout = {
                viewModel.logout()
            },
            onChangePassword = {},
            onLanguageClick = {},
            onNotificationsClick = {},
            onPrivacyPolicyClick = {},
            onFeedbackClick = {}
        )

        if (logoutState.isLoading) {
            CircularProgressIndicator()
        }
    }
}
