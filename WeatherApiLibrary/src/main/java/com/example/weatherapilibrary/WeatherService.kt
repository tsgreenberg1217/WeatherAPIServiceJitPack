package com.example.weatherapilibrary

import com.example.datalib.models.NetworkWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

object NetworkUtil {
    const val BaseUrl = "http://api.weatherstack.com/"
}

interface WeatherService {

    @GET("current")
    suspend fun getWeatherForCity(
        @Query("query") city: String,
        @Query("access_key") key: String = ""
    ): NetworkWeatherResponse
}