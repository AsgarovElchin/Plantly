package asgarov.elchin.plantly.core.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import asgarov.elchin.plantly.R

@Composable
fun BottomNavBar(navController: NavHostController) {
    val topLevelRoutes = listOf(
        TopLevelRoute("Reminder", NavigationRoute.ReminderRoute, R.drawable.alarm_icon),
        TopLevelRoute("Explore", NavigationRoute.ExploreRoute, R.drawable.search_icon),
        TopLevelRoute("Scan", NavigationRoute.ScanRoute, R.drawable.scan_icon),
        TopLevelRoute("My Garden", NavigationRoute.MyGarden, R.drawable.garden_icon),
        TopLevelRoute("Profile", NavigationRoute.ProfileRoute, R.drawable.person_icon)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        topLevelRoutes.forEachIndexed { index, route ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(route.iconResId),
                        contentDescription = route.name,
                        tint = if (index == selectedIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        route.name,
                        color = if (index == selectedIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    if (currentRoute != route.route.route) {
                        navController.navigate(route.route.route) {
                            popUpTo("main_graph") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}
