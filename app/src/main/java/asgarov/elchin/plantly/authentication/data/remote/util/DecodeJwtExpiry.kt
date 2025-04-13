package asgarov.elchin.plantly.authentication.data.remote.util

import android.util.Base64
import android.util.Log
import org.json.JSONObject

fun decodeJwtExpiry(token: String): Long {
    return try {
        val parts = token.split(".")
        if (parts.size < 2) {
            Log.e("JWT", "Invalid token format.")
            return 0L
        }
        val payload = String(Base64.decode(parts[1], Base64.URL_SAFE))
        val json = JSONObject(payload)
        json.getLong("exp")
    } catch (e: Exception) {
        Log.e("JWT", "Failed to decode JWT: ${e.message}")
        0L
    }
}
