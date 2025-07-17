package com.moashraf.domain.model.user.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val userName : String,
    val password : String
)
