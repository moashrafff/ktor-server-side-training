package com.moashraf.data.service

import com.moashraf.domain.model.blog.Blog
import com.moashraf.domain.model.blog.CreateBlogRequest
import com.moashraf.domain.model.blog.UpdateBlogRequest
import com.moashraf.domain.repo.BlogRepository
import com.moashraf.domain.service.BlogService

class BlogServiceImpl(private val blogRepository: BlogRepository) : BlogService {

    override suspend fun getAllBlogs(): List<Blog>? = blogRepository.getAllBlogs()

    override suspend fun createBlog(authorId: Int, request: CreateBlogRequest): Blog? =
        blogRepository.createBlog(authorId = authorId, request = request)

    override suspend fun updateBlog(id: Int, authorId: Int, request: UpdateBlogRequest): Blog? =
        blogRepository.updateBlog(id= id, authorId = authorId, request = request)

    override suspend fun deleteBlog(id: Int, authorId: Int): Boolean =
        blogRepository.deleteBlog(id= id, authorId = authorId)
}