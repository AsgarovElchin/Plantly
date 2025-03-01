package asgarov.elchin.plantly.onboarding.domain.model

import androidx.annotation.DrawableRes
import asgarov.elchin.plantly.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title:String,
    val description:String
){
    data object FirstPage: OnboardingModel(
        image = R.drawable.onboarding_first,
        title = "Identify Plants Instantly",
        description = "Snap a photo or upload an image, and let our smart AI recognize your plant in seconds." +
                "Discover names, species, and essential details with ease!"
    )
    data object SecondPage: OnboardingModel(
        image = R.drawable.onboarding_second,
        title = "Personalized Plant Care",
        description = "Get customized watering schedules, sunlight recommendations, and expert tips tailored to your plants." +
                "Keep them thriving with effortless care!")

    data object ThirdPage: OnboardingModel(
        image = R.drawable.onboarding_third,
        title = "Track and Grow Your Garden",
        description = "Save your favorite plants, monitor their growth, and receive timely reminders." +
                "Your personal plant journal keeps everything organized for a lush, healthy garden!")

}
