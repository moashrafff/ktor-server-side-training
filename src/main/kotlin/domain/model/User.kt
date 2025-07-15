package com.moashraf.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Int,
    val userName : String
)