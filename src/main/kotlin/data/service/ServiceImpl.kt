package com.moashraf.data.service

import com.moashraf.domain.service.UserService
import com.moashraf.domain.model.user.request.LoginRequest
import com.moashraf.domain.model.user.request.RegisterRequest
import com.moashraf.domain.model.user.User
import com.moashraf.domain.repo.UserRepository

class ServiceImpl(private val userRepository: UserRepository) : UserService {
    override suspend fun registerUser(registerRequest: RegisterRequest): User? =
        userRepository.createUser(registerRequest = registerRequest)

    override suspend fun loginUser(loginRequest: LoginRequest): User? =
        userRepository.authenticate(request = loginRequest)

    override suspend fun getUserById(id: Int): User? = userRepository.getUserById(id = id)
}