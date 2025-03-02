package asgarov.elchin.plantly.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: IconType
)

sealed class IconType {
    data class Vector(val imageVector: ImageVector) : IconType()
    data class DrawableRes(val resId: Int) : IconType()
}