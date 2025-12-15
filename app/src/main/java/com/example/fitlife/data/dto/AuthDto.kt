package com.example.fitlife.data.dto


data class PlanDto(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val nivel: String,
    val tipo: String,
    val duracionMinutos: Int,
    val precioCLP: Int
)


data class UploadResponse(val url: String)


