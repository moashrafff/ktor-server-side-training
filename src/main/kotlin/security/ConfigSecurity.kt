package com.moashraf.security

import com.moashraf.config.jwtVerifier
import com.moashraf.domain.service.UserService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configSecurity(userService: UserService) {
    val jwtSecret = System.getenv("jwt_secret")
    val jwtRealm = environment.config.property("jwt.realm").getString()
    val jwtIssuer = environment.config.property("jwt.issuer").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtClaimField = environment.config.property("jwt.claimField").getString()

    val verifier = jwtVerifier()

    install(Authentication) {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(verifier)
            validate { cred ->
                cred.payload.getClaim(
                    jwtClaimField
                ).asInt()?.let {
                    userService.getUserById(it)
                }
            }
        }
    }
}