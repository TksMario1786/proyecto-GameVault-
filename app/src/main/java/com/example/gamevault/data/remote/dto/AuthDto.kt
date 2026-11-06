package com.example.gamevault.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    val email: String,
    val password: String,
    val nombre: String? = null
)

@Serializable
data class AuthResponseDto(
    val accessToken: String,
    val user: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val email: String
)