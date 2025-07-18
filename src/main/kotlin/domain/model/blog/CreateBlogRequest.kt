package com.moashraf.domain.model.blog

import kotlinx.serialization.Serializable

@Serializable
data class CreateBlogRequest(
    val title: String, val content: String
)
