package com.example.weatherapilibrary

import com.example.datalib.mappers.NetworkMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object WeatherServiceModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }.build()
    }

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesWeatherApi(gson: Gson, client: OkHttpClient): Retrofit.Builder {

        return Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    fun providesWeatherService(retrofit: Retrofit.Builder): WeatherService {
        return retrofit.build().create(WeatherService::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
object NetworkMappers{
    @Provides
    fun providesNetworkMapper(): NetworkMapper = NetworkMapper()

}