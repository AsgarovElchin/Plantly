package asgarov.elchin.plantly.core.di

import asgarov.elchin.plantly.authentication.AuthInterceptor
import asgarov.elchin.plantly.authentication.TokenAuthenticator
import asgarov.elchin.plantly.authentication.data.remote.util.MoshiUnitAdapter
import asgarov.elchin.plantly.core.utils.Constants
import asgarov.elchin.plantly.feature_explore.data.remote.dto.PruningCountAdapter
import asgarov.elchin.plantly.feature_explore.data.remote.dto.PruningCountData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }


    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        val baseMoshi = Moshi.Builder().build()
        val pruningCountDataAdapter = baseMoshi.adapter(PruningCountData::class.java)

        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Unit::class.java, MoshiUnitAdapter)
            .add(PruningCountAdapter(pruningCountDataAdapter))
            .build()
    }

    @Provides
    @Singleton
    @Named("PerenualApi")
    fun providePerenualRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.PERENNUAL_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    @Named("MainApi")
    fun provideMainRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    @Named("GeminiApi")
    fun provideGeminiRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.GEMINI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}