package com.moashraf.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val userName : String,
    val password : String
)
