package asgarov.elchin.plantly.feature_reminder.domain.model

enum class ReminderType {
    MISTING,
    WATERING,
    ROTATING,
    FERTILIZING;

    companion object {
        fun fromString(value: String): ReminderType? {
            return when (value.uppercase()) {
                "WATERING" -> WATERING
                "FERTILIZING" -> FERTILIZING
                "ROTATING" -> ROTATING
                "FERTILIZING"-> FERTILIZING
                else -> null // Or throw an IllegalArgumentException if you want strict enforcement
            }
        }
}}