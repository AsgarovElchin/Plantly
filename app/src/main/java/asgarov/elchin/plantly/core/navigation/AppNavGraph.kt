package asgarov.elchin.plantly.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import asgarov.elchin.plantly.authentication.presentation.screen.create_new_password.CreateNewPasswordScreen
import asgarov.elchin.plantly.authentication.presentation.screen.forgot_password.ForgotPasswordScreen
import asgarov.elchin.plantly.authentication.presentation.screen.login.LoginScreen
import asgarov.elchin.plantly.authentication.presentation.screen.otp_verification.OTPVerificationScreen
import asgarov.elchin.plantly.authentication.presentation.screen.password_changed.PasswordChangedScreen
import asgarov.elchin.plantly.authentication.presentation.screen.register.RegisterEmailScreen
import asgarov.elchin.plantly.authentication.presentation.screen.register.RegisterScreen
import asgarov.elchin.plantly.authentication.presentation.screen.welcome.WelcomeScreen
import asgarov.elchin.plantly.feature_explore.presentation.ExploreScreen
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.PlantDetailScreen
import asgarov.elchin.plantly.feature_my_garden.presentation.MyGardenScreen
import asgarov.elchin.plantly.feature_profile.presentation.ProfileScreen
import asgarov.elchin.plantly.feature_reminder.presentation.EditReminderScreen
import asgarov.elchin.plantly.feature_reminder.presentation.ReminderScreen
import asgarov.elchin.plantly.feature_reminder.presentation.SetReminderScreen
import asgarov.elchin.plantly.feature_scan.presentation.ScanScreen
import asgarov.elchin.plantly.onboarding.presentation.OnBoardingViewModel
import asgarov.elchin.plantly.onboarding.presentation.OnboardingScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val showOnboarding by viewModel.showOnboarding.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) return

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (showOnboarding) "onboarding_graph" else "authentication_graph"
    ) {

        navigation(
            startDestination = NavigationRoute.OnboardingRoute.route,
            route = "onboarding_graph"
        ) {
            composable(NavigationRoute.OnboardingRoute.route) {
                OnboardingScreen(navController)
            }
        }

        navigation(
            startDestination = NavigationRoute.WelcomeRoute.route,
            route = "authentication_graph"
        ) {
            composable(NavigationRoute.WelcomeRoute.route) {
                WelcomeScreen(navController)
            }
            composable(NavigationRoute.LoginRoute.route) {
                LoginScreen(navController)
            }
            composable(
                route = NavigationRoute.RegisterRoute.route,
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) {
                RegisterScreen(navController)
            }

            composable(NavigationRoute.ForgotPasswordRoute.route) {
                ForgotPasswordScreen(navController)
            }
            composable(
                route = NavigationRoute.OTPVerificationRoute.route,
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType },
                    navArgument("type") { type = NavType.StringType }
                )
            ) {
                OTPVerificationScreen(navController)
            }

            composable(
                route = NavigationRoute.CreateNewPassword.route,
                arguments = listOf(navArgument("email") { type = NavType.StringType })
            ) {
                CreateNewPasswordScreen(navController)
            }
            composable(NavigationRoute.PasswordChanged.route) {
                PasswordChangedScreen(navController)
            }
        }

        navigation(
            startDestination = NavigationRoute.ReminderRoute.route,
            route = "main_graph"
        ) {
            composable(NavigationRoute.ReminderRoute.route) {
                ReminderScreen(navController)
            }
            composable(NavigationRoute.ExploreRoute.route) {
                ExploreScreen(navController)
            }
            composable(NavigationRoute.ScanRoute.route) {
                ScanScreen(navController)
            }
            composable(NavigationRoute.MyGarden.route) {
                MyGardenScreen(navController)
            }
            composable(NavigationRoute.ProfileRoute.route) {
                ProfileScreen()
            }
            composable(
                route = NavigationRoute.PlantDetailRoute.route,
                arguments = listOf(navArgument("plantId") { type = NavType.IntType })
            ) {
                PlantDetailScreen(navController)
            }
            composable(
                route = NavigationRoute.SetReminderRoute.route,
                arguments = listOf(
                    navArgument("plantId") { type = NavType.LongType },
                    navArgument("plantName") { type = NavType.StringType }
                )
            ) {
                SetReminderScreen(navController)
            }
            composable(
                route = NavigationRoute.EditReminderRoute.route,
                arguments = listOf(
                    navArgument("reminderId") { type = NavType.LongType },
                    navArgument("reminderType") { type = NavType.StringType }
                )
            ) {
                EditReminderScreen(navController)
            }
            composable(NavigationRoute.RegisterEmailRoute.route) {
                RegisterEmailScreen(navController)
            }
        }
    }
}