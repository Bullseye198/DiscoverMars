package com.example.domain.image.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import com.example.domain.image.model.Camera

@Keep
@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "camera")
    val camera: Camera,
    @Json(name = "earth_date")
    val earthDate: String, // 2015-06-03
    @Json(name = "id")
    val id: Int, // 102685
    @Json(name = "img_src")
    val imgSrc: String,
    @Json(name = "rover")
    val rover: Rover,
    @Json(name = "sol")
    val sol: Int // 1004
)