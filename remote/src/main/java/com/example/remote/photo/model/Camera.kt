package com.example.remote.photo.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class Camera(
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "id")
    val id: Int, // 20
    @Json(name = "name")
    val name: String, // FHAZ
    @Json(name = "rover_id")
    val roverId: Int // 5
)