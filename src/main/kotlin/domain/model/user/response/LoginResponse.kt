package com.moashraf.domain.model.user.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: String,
    val userName: String,
    val token: String
)
