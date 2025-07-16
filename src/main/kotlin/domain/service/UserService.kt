package com.moashraf.domain.service

import com.moashraf.domain.model.user.request.LoginRequest
import com.moashraf.domain.model.user.request.RegisterRequest
import com.moashraf.domain.model.user.User

interface UserService {
    suspend fun registerUser(registerRequest: RegisterRequest): User?
    suspend fun loginUser(loginRequest: LoginRequest): User?
    suspend fun getUserById(id: Int): User?
}