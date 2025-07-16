package com.moashraf.domain.repo

import com.moashraf.domain.model.blog.Blog
import com.moashraf.domain.model.blog.CreateBlogRequest
import com.moashraf.domain.model.blog.UpdateBlogRequest

interface BlogRepository {
    suspend fun getAllBlogs() : List<Blog>?
    suspend fun createBlog(authorId: Int, request: CreateBlogRequest): Blog?
    suspend fun updateBlog(id: Int,authorId: Int, request: UpdateBlogRequest): Blog?
    suspend fun deleteBlog(id: Int, authorId: Int): Boolean
}