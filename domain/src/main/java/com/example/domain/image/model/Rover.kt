package com.example.domain.image.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import com.example.domain.image.model.CameraX

@Keep
@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name = "cameras")
    val cameras: List<CameraX>,
    @Json(name = "id")
    val id: Int, // 5
    @Json(name = "landing_date")
    val landingDate: String,
    @Json(name = "launch_date")
    val launchDate: String,
    @Json(name = "max_date")
    val maxDate: String,
    @Json(name = "max_sol")
    val maxSol: Int,
    @Json(name = "name")
    val name: String, // Name is Curiosity
    @Json(name = "status")
    val status: String,
    @Json(name = "total_photos")
    val totalPhotos: Int
)