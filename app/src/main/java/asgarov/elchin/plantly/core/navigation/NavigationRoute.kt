package asgarov.elchin.plantly.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute(val route: String) {

    @Serializable
    data object OnboardingRoute : NavigationRoute("onboarding")

    @Serializable
    data object WelcomeRoute : NavigationRoute("welcome")

    @Serializable
    data object LoginRoute : NavigationRoute("login")

    @Serializable
    data object RegisterRoute : NavigationRoute("register?email={email}") {
        fun createRoute(email: String) = "register?email=$email"
    }

    @Serializable
    data object ForgotPasswordRoute : NavigationRoute("forgot_password")

    @Serializable
    data object OTPVerificationRoute : NavigationRoute("otp_verification/{email}/{type}") {
        fun createRoute(email: String, type: String) = "otp_verification/$email/$type"
    }

    @Serializable
    data object CreateNewPassword : NavigationRoute("create_new_password/{email}") {
        fun createRoute(email: String) = "create_new_password/$email"
    }

    @Serializable
    data object PasswordChanged : NavigationRoute("password_changed")

    @Serializable
    data object ReminderRoute : NavigationRoute("reminder")

    @Serializable
    data object ExploreRoute : NavigationRoute("explore")

    @Serializable
    data object ScanRoute : NavigationRoute("scan")

    @Serializable
    data object MyGarden : NavigationRoute("my_garden")

    @Serializable
    data object ProfileRoute : NavigationRoute("profile")

    @Serializable
    data object PlantDetailRoute : NavigationRoute("plant_detail/{plantId}") {
        fun createRoute(plantId: Int) = "plant_detail/$plantId"
    }

    @Serializable
    data object SetReminderRoute : NavigationRoute("reminder/{plantId}/{plantName}") {
        fun createRoute(plantId: Long, plantName: String) = "reminder/$plantId/$plantName"
    }

    @Serializable
    data object EditReminderRoute : NavigationRoute("edit_reminder/{reminderId}/{reminderType}") {
        fun createRoute(reminderId: Long, reminderType: String) = "edit_reminder/$reminderId/$reminderType"
    }

    @Serializable
    data object RegisterEmailRoute : NavigationRoute("register_email")
}