package com.example.remote.photo.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.domain.image.model.Camera


@JsonClass(generateAdapter = true)
data class CameraRaw(
    @Json(name = "full_name")
    val fullName: String, // Front Hazard Avoidance Camera
    @Json(name = "id")
    val id: Int, // 20
    @Json(name = "name")
    val name: String, // FHAZ
    @Json(name = "rover_id")
    val roverId: Int // 5
)

fun CameraRaw.mapToDomain() = Camera(
    fullName = fullName,
    name = name,
    roverId = roverId
)