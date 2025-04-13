package asgarov.elchin.plantly.feature_reminder.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class ReminderType {
    @Json(name = "MISTING")
    MISTING,

    @Json(name = "WATERING")
    WATERING,

    @Json(name = "ROTATING")
    ROTATING,

    @Json(name = "FERTILIZING")
    FERTILIZING
}
