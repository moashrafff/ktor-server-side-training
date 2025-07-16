package com.moashraf.route

import com.moashraf.config.generateToken
import com.moashraf.domain.model.user.request.LoginRequest
import com.moashraf.domain.model.user.request.RegisterRequest
import com.moashraf.domain.model.user.response.LoginResponse
import com.moashraf.domain.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRoute(userService: UserService) {
    routing {
        route("/api/users") {
            register(userService = userService)
            post("/login") {
                val request = call.receive<LoginRequest>()
                val user = userService.loginUser(loginRequest = request)

                if (user != null) {
                    val token = generateToken(user)

                    if (token != null) {
                        call.respond(
                            HttpStatusCode.OK,
                            LoginResponse(
                                id = user.id.toString(),
                                userName = user.userName,
                                token = token
                            )
                        )
                    } else {
                        call.respond(
                            HttpStatusCode.Unauthorized,
                            "Token generation failed"
                        )
                    }
                } else {
                    call.respond(
                        HttpStatusCode.Unauthorized,
                        "Invalid username or password"
                    )
                }
            }
            get("/id") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.Conflict, "Invalid Id")
                    return@get
                }

                val user = userService.getUserById(id)

                if (user == null)
                    call.respond(HttpStatusCode.Conflict, "User Not Found")
                else
                    call.respond(HttpStatusCode.OK, user)

            }
        }
    }
}

fun Route.register(userService: UserService) {
    post("/register") {
        val request = call.receive<RegisterRequest>()
        val user = userService.registerUser(registerRequest = request)
        if (user != null)
            call.respond(HttpStatusCode.Created, user)
        else
            call.respond(HttpStatusCode.Conflict, "User name already exists")
    }
}