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

    @Serializable
    data object WelcomeRoute: NavigationRoute("welcome")

    @Serializable
    data object LoginRoute: NavigationRoute("login")

    @Serializable
    data object RegisterRoute: NavigationRoute("register")

    @Serializable
    data object ForgotPasswordRoute: NavigationRoute("forgot_password")

    @Serializable
    data object OTPVerificationRoute: NavigationRoute("otp_verification")

    @Serializable
    data object CreateNewPassword: NavigationRoute("create_new_password")

    @Serializable
    data object PasswordChanged: NavigationRoute("password_changed")

    @Serializable
    data object PlantScreenRoute : NavigationRoute("plant_screen/{categoryName}") {
        fun createRoute(categoryName: String) = "plant_screen/$categoryName"
    }



}
