package com.example.remote.photo

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object PhotoServiceFactory {

    fun makePhotoService(chuckerInterceptor: Interceptor): PhotoService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                makeOkHttpClient(
                    chuckerInterceptor
                )
            )
            .build()

        return retrofit.create(PhotoService::class.java)
    }


    private fun makeOkHttpClient(
        chuckerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}