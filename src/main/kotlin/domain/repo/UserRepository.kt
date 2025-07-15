package com.moashraf.domain.repo

import com.moashraf.domain.model.LoginRequest
import com.moashraf.domain.model.RegisterRequest
import com.moashraf.domain.model.User

interface UserRepository {
    fun createUser(registerRequest: RegisterRequest): User?
    fun getUserById(id : Int): User?
    fun authenticate(request: LoginRequest): User?
    fun hashPassword(password: String) : String
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean
}