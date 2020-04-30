package com.example.discovermars.core.injection.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.remote.photo.PhotoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object PhotoServiceFactory {

    fun makePhotoService(context: Context): PhotoService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(makeOkHttpClient(context))
            .build()

        return retrofit.create(PhotoService::class.java)
    }


    private fun makeOkHttpClient(
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}