package com.moashraf.domain.service

import com.moashraf.domain.model.LoginRequest
import com.moashraf.domain.model.RegisterRequest
import com.moashraf.domain.model.User

interface UserService {
    suspend fun registerUser(registerRequest: RegisterRequest): User?
    suspend fun loginUser(loginRequest: LoginRequest): User?
    suspend fun getUserById(id: Int): User?
}