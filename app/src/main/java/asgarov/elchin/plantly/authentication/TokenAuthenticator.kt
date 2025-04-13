package asgarov.elchin.plantly.authentication

import android.util.Log
import asgarov.elchin.plantly.authentication.domain.repository.AuthRepository
import asgarov.elchin.plantly.core.utils.Resource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: dagger.Lazy<AuthRepository>
) : Authenticator {

    private val mutex = Mutex()

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) return null

        return runBlocking {
            mutex.withLock {
                val refreshToken = tokenManager.refreshToken.firstOrNull() ?: return@runBlocking null
                Log.d("TokenAuthenticator", "401 received. Attempting token refresh with: $refreshToken")

                val refreshResult = authRepository.get().refreshToken(refreshToken).firstOrNull()

                when (refreshResult) {
                    is Resource.Success -> {
                        val newAccessToken = refreshResult.data ?: return@runBlocking null
                        Log.d("TokenAuthenticator", "New access token obtained: $newAccessToken")
                        tokenManager.saveAccessToken(newAccessToken)

                        return@runBlocking response.request.newBuilder()
                            .header("Authorization", "Bearer $newAccessToken")
                            .build()
                    }

                    is Resource.Error -> {
                        Log.e("TokenAuthenticator", "Refresh failed: ${refreshResult.message}")
                        null
                    }

                    else -> {
                        Log.w("TokenAuthenticator", "Unexpected refresh result: $refreshResult")
                        null
                    }
                }
            }
        }
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }
}
