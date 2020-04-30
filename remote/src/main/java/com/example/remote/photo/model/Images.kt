package com.example.remote.photo.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "photos")
    val photos: List<Photo>
)