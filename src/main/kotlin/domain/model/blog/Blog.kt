package com.moashraf.domain.model.blog

import kotlinx.serialization.Serializable

@Serializable
data class Blog(val id: Int, val title: String, val content: String, val authorId: Int)
