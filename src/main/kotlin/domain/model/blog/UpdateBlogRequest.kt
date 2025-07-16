package com.moashraf.domain.model.blog

import kotlinx.serialization.Serializable

@Serializable
data class UpdateBlogRequest(
    val title: String? = null,
    val content: String? = null
)
