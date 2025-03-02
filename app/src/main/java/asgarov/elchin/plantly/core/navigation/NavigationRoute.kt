package asgarov.elchin.plantly.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute(val route:String) {
    @Serializable
    data object OnboardingRoute : NavigationRoute("onboarding")

    @Serializable
    data object ReminderRoute : NavigationRoute("reminder")

    @Serializable
    data object ExploreRoute : NavigationRoute("explore")

    @Serializable
    data object ScanRoute: NavigationRoute("scan")

    @Serializable
    data object MyGarden: NavigationRoute("my_garden")

    @Serializable
    data object ProfileRoute: NavigationRoute("profile")



}
