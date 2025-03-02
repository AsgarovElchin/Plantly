package asgarov.elchin.plantly.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import asgarov.elchin.plantly.feature_explore.presentation.ExploreScreen
import asgarov.elchin.plantly.feature_my_garden.presentation.MyGardenScreen
import asgarov.elchin.plantly.feature_profile.presentation.ProfileScreen
import asgarov.elchin.plantly.feature_reminder.presentation.ReminderScreen
import asgarov.elchin.plantly.feature_scan.ScanScreen
import asgarov.elchin.plantly.onboarding.presentation.OnBoardingViewModel
import asgarov.elchin.plantly.onboarding.presentation.OnboardingScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: OnBoardingViewModel = hiltViewModel()

    val showOnboarding by viewModel.showOnboarding.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        return
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (showOnboarding) "onboarding_graph" else "main_graph"
    ) {


        navigation(startDestination = NavigationRoute.OnboardingRoute.route, route = "onboarding_graph") {
            composable(NavigationRoute.OnboardingRoute.route) {
                OnboardingScreen(navController)
            }
        }


        navigation(startDestination = NavigationRoute.ReminderRoute.route, route = "main_graph") {
            composable(NavigationRoute.ReminderRoute.route) {
                ReminderScreen()
            }
            composable(NavigationRoute.ExploreRoute.route) {
                ExploreScreen()
            }
            composable(NavigationRoute.ScanRoute.route) {
                ScanScreen()
            }
            composable(NavigationRoute.MyGarden.route) {
                MyGardenScreen()
            }
            composable(NavigationRoute.ProfileRoute.route) {
                ProfileScreen()
            }
        }
    }
}
