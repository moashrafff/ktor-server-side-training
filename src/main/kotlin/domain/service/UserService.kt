package com.moashraf.domain.service

import com.moashraf.domain.model.user.LoginRequest
import com.moashraf.domain.model.user.RegisterRequest
import com.moashraf.domain.model.user.User

interface UserService {
    suspend fun registerUser(registerRequest: RegisterRequest): User?
    suspend fun loginUser(loginRequest: LoginRequest): User?
    suspend fun getUserById(id: Int): User?
}