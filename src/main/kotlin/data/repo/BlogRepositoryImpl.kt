package com.moashraf.data.repo

import com.moashraf.data.db.entity.BlogEntity
import com.moashraf.data.db.entity.UserEntity
import com.moashraf.data.db.utils.dbQuery
import com.moashraf.domain.model.blog.Blog
import com.moashraf.domain.model.blog.CreateBlogRequest
import com.moashraf.domain.model.blog.UpdateBlogRequest
import com.moashraf.domain.repo.BlogRepository

class BlogRepositoryImpl : BlogRepository {
    override suspend fun getAllBlogs(): List<Blog>? = dbQuery {
        BlogEntity.all().map {
            Blog(id = it.id.value, title = it.title, content = it.content, authorId = it.author.id.value)
        }
    }


    override suspend fun createBlog(authorId: Int, request: CreateBlogRequest): Blog? = dbQuery {
        val authorValue = UserEntity.findById(authorId) ?: return@dbQuery null
        BlogEntity.new {
            this.title = request.title
            this.content = request.content
            this.author = authorValue
        }.toBlog()
    }


    override suspend fun updateBlog(id: Int, authorId: Int, request: UpdateBlogRequest): Blog? = dbQuery {
        val blog = BlogEntity.findById(id) ?: return@dbQuery null
        if (blog.author.id.value != authorId) return@dbQuery null
        request.title?.let {
            blog.title = it
        }

        request.content?.let {
            blog.content = it
        }

        blog.toBlog()
    }

    override suspend fun deleteBlog(id: Int, authorId: Int): Boolean = dbQuery {
        try {
            val blog = BlogEntity.findById(id) ?: return@dbQuery null
            if (blog.author.id.value != authorId) return@dbQuery null
            blog.delete()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    } == true
}