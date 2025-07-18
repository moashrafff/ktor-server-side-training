package com.moashraf.route

import com.moashraf.domain.model.blog.CreateBlogRequest
import com.moashraf.domain.model.blog.UpdateBlogRequest
import com.moashraf.domain.model.user.User
import com.moashraf.domain.service.BlogService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.blogRoute(blogService: BlogService) {
    routing {
        authenticate("auth-jwt") {
            route("/api/blogs") {
                get {
                    val blogs = blogService.getAllBlogs()
                    if (blogs.isNullOrEmpty()) {
                        call.respond(HttpStatusCode.NotFound, "blogs are empty")
                    } else {
                        call.respond(HttpStatusCode.OK, blogs)
                    }
                }

                post("/createBlog") {
                    val request = call.receive<CreateBlogRequest>()
                    val user = call.authentication.principal<User>()
                    if (user == null)
                        call.respond(HttpStatusCode.Unauthorized, "Invalid Token")
                    else {
                        val blog = blogService.createBlog(authorId = user.id, request = request)
                        if (blog != null)
                            call.respond(HttpStatusCode.Created, blog)
                        else
                            call.respond(HttpStatusCode.Conflict, "Something Went Wrong!")
                    }
                }

                put("/{blogId}") {
                    val request = call.receive<UpdateBlogRequest>()
                    val blogId = call.parameters["blogId"]?.toIntOrNull()
                    if (blogId == null) {
                        call.respond(HttpStatusCode.BadRequest, "Invalid Id")
                        return@put
                    }
                    val user = call.authentication.principal<User>()
                    if (user == null)
                        call.respond(HttpStatusCode.Unauthorized, "Invalid Token")
                    else {
                        val blog = blogService.updateBlog(authorId = user.id, request = request, id = blogId)
                        if (blog != null)
                            call.respond(HttpStatusCode.Created, blog)
                        else
                            call.respond(HttpStatusCode.Conflict, "Something Went Wrong!")
                    }
                }

                delete("/{blogId}") {
                    val blogId = call.parameters["blogId"]?.toIntOrNull()
                    if (blogId == null) {
                        call.respond(HttpStatusCode.BadRequest, "Invalid Id")
                        return@delete
                    }
                    val user = call.authentication.principal<User>()
                    if (user == null)
                        call.respond(HttpStatusCode.Unauthorized, "Invalid Token")
                    else {
                        val isBlogDeleted = blogService.deleteBlog(id = blogId, authorId = user.id )
                        if (isBlogDeleted)
                            call.respond(HttpStatusCode.OK, "Blog Deleted Successfully")
                        else
                            call.respond(HttpStatusCode.Conflict, "Something Went Wrong!")
                    }
                }
            }
        }
    }
}