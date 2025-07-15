package com.moashraf.domain.repo

import com.moashraf.domain.model.LoginRequest
import com.moashraf.domain.model.RegisterRequest
import com.moashraf.domain.model.User

interface UserRepository {
    suspend fun createUser(registerRequest: RegisterRequest): User?
    suspend fun getUserById(id: Int): User?
    suspend fun authenticate(request: LoginRequest): User?
    fun hashPassword(password: String): String
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean
}