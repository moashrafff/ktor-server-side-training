package com.moashraf.domain.model.user.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val userName : String,
    val email : String,
    val password : String
)
