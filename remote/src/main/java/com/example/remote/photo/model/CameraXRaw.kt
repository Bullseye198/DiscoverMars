package com.example.remote.photo.model
import com.example.domain.image.model.CameraX
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CameraXRaw(
    @Json(name = "full_name")
    val fullName: String,  // Front Hazard Avoidance Camera
    @Json(name = "name")
    val name: String // FHAZ
)

fun CameraXRaw.mapDomainCameraXModel() = CameraX(
    fullName = fullName,
    name = name
)