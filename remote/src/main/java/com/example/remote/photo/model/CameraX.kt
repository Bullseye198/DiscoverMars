package com.example.remote.photo.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CameraX(
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "name")
    val name: String // FHAZ
)