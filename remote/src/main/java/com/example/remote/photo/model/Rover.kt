package com.example.remote.photo.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name = "cameras")
    val cameras: List<CameraX>,
    @Json(name = "id")
    val id: Int, // 5
    @Json(name = "landing_date")
    val landingDate: String, // 2012-08-06
    @Json(name = "launch_date")
    val launchDate: String, // 2011-11-26
    @Json(name = "max_date")
    val maxDate: String, // 2020-06-04
    @Json(name = "max_sol")
    val maxSol: Int, // 2783
    @Json(name = "name")
    val name: String, // Name is Curiosity
    @Json(name = "status")
    val status: String, // active
    @Json(name = "total_photos")
    val totalPhotos: Int // 424444
)