package com.moashraf

import com.moashraf.db.initDb
import com.moashraf.di.configKoin
import com.moashraf.domain.service.BlogService
import com.moashraf.domain.service.UserService
import com.moashraf.route.blogRoute
import com.moashraf.route.userRoute
import com.moashraf.security.configSecurity
import io.ktor.server.application.*
import org.koin.ktor.ext.get

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configKoin()
    configureContentNegotiation()
    initDb()

    val userService = get<UserService>()
    val blogService = get<BlogService>()

    configSecurity(userService = userService)
    userRoute(userService = userService)
    blogRoute(blogService = blogService)
}
