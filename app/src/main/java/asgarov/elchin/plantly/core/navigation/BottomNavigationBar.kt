package asgarov.elchin.plantly.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import asgarov.elchin.plantly.R

@Composable
fun BottomNavBar(navController: NavHostController) {
    val topLevelRoutes = listOf(
        TopLevelRoute("Reminder", NavigationRoute.ReminderRoute, IconType.DrawableRes(R.drawable.alarm_icon)),
        TopLevelRoute("Explore", NavigationRoute.ExploreRoute, IconType.DrawableRes(R.drawable.search_icon)),
        TopLevelRoute("Scan", NavigationRoute.ScanRoute, IconType.DrawableRes(R.drawable.scan_icon)),
        TopLevelRoute("My Garden", NavigationRoute.MyGarden, IconType.DrawableRes(R.drawable.garden_icon)),
        TopLevelRoute("Profile", NavigationRoute.ProfileRoute, IconType.DrawableRes(R.drawable.person_icon))
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        topLevelRoutes.forEachIndexed { index, route ->
            NavigationBarItem(
                icon = {
                    when (route.icon) {
                        is IconType.Vector -> Icon(route.icon.imageVector, contentDescription = route.name)
                        is IconType.DrawableRes -> Icon(
                            painterResource(route.icon.resId),
                            contentDescription = route.name
                        )
                    }
                },
                label = { Text(route.name) },
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
                }
            )
        }
    }
}