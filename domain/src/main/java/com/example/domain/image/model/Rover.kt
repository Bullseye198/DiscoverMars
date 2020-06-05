package com.example.domain.image.model

data class Rover(
    val cameras: List<CameraX>,
    val id: Int, // 5
    val landingDate: String, // 2012-08-06
    val launchDate: String, // 2011-11-26
    val maxDate: String, // 2020-06-04
    val maxSol: Int, // 2783
    val name: String, // Name is Curiosity
    val status: String, // active
    val totalPhotos: Int // 424444
)