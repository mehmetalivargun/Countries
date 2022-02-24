package com.mehmetalivargun.countries.di

import android.os.SystemClock
import com.mehmetalivargun.countries.api.CountryService
import com.mehmetalivargun.countries.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests=1
        val interceptor = Interceptor { chain ->
            SystemClock.sleep(1300)// due to api rate limit
            chain.proceed(chain.request())
        }

        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                    builder.header("x-rapidapi-key", "4196b273aamsh043a22253c5ed6ep167587jsnecd31f0ea0ea")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }
            .dispatcher(dispatcher)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideService(): CountryService {
        return Retrofit.Builder()
            .baseUrl("https://wft-geo-db.p.rapidapi.com/v1/geo/countries/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(): CountryRepository {
        return CountryRepository(provideService())
    }


}