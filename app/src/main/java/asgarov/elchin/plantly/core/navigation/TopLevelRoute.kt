package asgarov.elchin.plantly.core.navigation

import androidx.annotation.DrawableRes

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    @DrawableRes val iconResId: Int
)
