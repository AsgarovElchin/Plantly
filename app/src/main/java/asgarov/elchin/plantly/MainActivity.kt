package asgarov.elchin.plantly


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import asgarov.elchin.plantly.core.navigation.AppNavGraph
import asgarov.elchin.plantly.core.navigation.BottomNavBar
import asgarov.elchin.plantly.core.navigation.NavigationRoute
import asgarov.elchin.plantly.ui.theme.PlantlyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantlyTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()

                val showBottomBar = backStackEntry.value?.destination?.route?.let { route ->
                    route != NavigationRoute.OnboardingRoute.route && route != "onboarding_graph"
                } ?: false

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {if(showBottomBar) BottomNavBar(navController)}
                ) { innerPadding ->
                    AppNavGraph(
                        navController= navController,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

