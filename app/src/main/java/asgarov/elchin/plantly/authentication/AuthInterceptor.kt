package asgarov.elchin.plantly.authentication

import android.util.Log
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val urlPath = request.url.encodedPath

        val publicEndpoints = listOf(
            "/login",
            "/register",
            "/refresh",
            "/forgot-password",
            "/reset-password"
        )

        val isOtpEndpoint = urlPath.startsWith("/api/otp")

        if (publicEndpoints.any { urlPath.contains(it) } || isOtpEndpoint) {
            Log.d("AuthInterceptor", "Skipping token for public endpoint: $urlPath")
            return chain.proceed(request)
        }

        val accessToken = runBlocking { tokenManager.accessToken.first() }

        Log.d("AuthInterceptor", "Intercepting request: $urlPath")
        Log.d("AuthInterceptor", "Using access token: $accessToken")

        val authenticatedRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
