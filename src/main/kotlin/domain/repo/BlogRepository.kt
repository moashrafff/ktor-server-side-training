package com.moashraf.domain.repo

import com.moashraf.domain.model.blog.Blog
import com.moashraf.domain.model.blog.UpdateBlogRequest

interface BlogRepository {
    suspend fun getAllBlogs() : List<Blog>?
    suspend fun updateBlog(id: Int, request: UpdateBlogRequest): Blog?
}