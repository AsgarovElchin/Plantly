package asgarov.elchin.plantly


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import asgarov.elchin.plantly.authentication.StartupViewModel
import asgarov.elchin.plantly.core.navigation.AppNavGraph
import asgarov.elchin.plantly.core.navigation.BottomNavBar
import asgarov.elchin.plantly.core.navigation.NavigationRoute
import asgarov.elchin.plantly.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme(dynamicColor = false) {
                val startupViewModel: StartupViewModel = hiltViewModel()
                val startDestination by startupViewModel.startDestination.collectAsState()
                val navController = rememberNavController()

                if (startDestination != null) {
                    Scaffold(
                        bottomBar = {
                            val route = navController.currentBackStackEntryAsState().value?.destination?.route
                            val showBottomBar = route in listOf(
                                NavigationRoute.ReminderRoute.route,
                                NavigationRoute.ExploreRoute.route,
                                NavigationRoute.MyGarden.route,
                                NavigationRoute.ProfileRoute.route,
                                NavigationRoute.ScanRoute.route
                            )
                            if (showBottomBar) BottomNavBar(navController)
                        }
                    ) { padding ->
                        AppNavGraph(
                            navController = navController,
                            startDestination = startDestination!!,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}

