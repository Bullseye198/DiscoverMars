package com.example.domain.image.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class CameraX(
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "name")
    val name: String // FHAZ
)