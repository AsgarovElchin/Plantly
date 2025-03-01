package asgarov.elchin.plantly.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import asgarov.elchin.plantly.MainScreen
import asgarov.elchin.plantly.onboarding.presentation.OnBoardingViewModel
import asgarov.elchin.plantly.onboarding.presentation.OnboardingScreen

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: OnBoardingViewModel = hiltViewModel()

    val showOnboarding by viewModel.showOnboarding.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    if (isLoading) {
        return
    }

    NavHost(
        navController = navController,
        startDestination = if (showOnboarding) "onboarding" else "main"
    ) {
        composable("onboarding") { OnboardingScreen(navController) }
        composable("main") { MainScreen() }
    }
}