package asgarov.elchin.plantly.feature_reminder.domain.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class PreviousData {
    @Json(name = "TODAY")
    TODAY,

    @Json(name = "YESTERDAY")
    YESTERDAY,

    @Json(name = "ONE_WEEK_AGO")
    ONE_WEEK_AGO,

    @Json(name = "TWO_WEEKS_AGO")
    TWO_WEEKS_AGO,

    @Json(name = "THREE_WEEKS_AGO")
    THREE_WEEKS_AGO,

    @Json(name = "A_MONTH_AGO")
    A_MONTH_AGO,

    @Json(name = "MORE_THAN_A_MONTH")
    MORE_THAN_A_MONTH,

    @Json(name = "DONT_REMEMBER")
    DONT_REMEMBER
}
