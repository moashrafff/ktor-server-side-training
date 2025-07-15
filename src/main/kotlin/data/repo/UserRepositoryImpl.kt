package com.moashraf.data.repo

import com.moashraf.db.entity.UserEntity
import com.moashraf.db.utils.dbQuery
import com.moashraf.domain.model.LoginRequest
import com.moashraf.domain.model.RegisterRequest
import com.moashraf.domain.model.User
import com.moashraf.domain.repo.UserRepository
import db.tables.UserTable
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl : UserRepository {
    override suspend fun createUser(registerRequest: RegisterRequest): User? {
        return dbQuery {
            val user = UserEntity.find { UserTable.userName eq registerRequest.userName }.firstOrNull()
            if (user != null) {
                return@dbQuery user.toUser()
            }
            val newUser = UserEntity.new {
                userName = registerRequest.userName
                password = hashPassword(registerRequest.password)
            }
            return@dbQuery newUser.toUser()
        }
    }

    override suspend fun getUserById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(request: LoginRequest): User? {
        TODO("Not yet implemented")
    }

    override fun hashPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())


    override fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean =
        BCrypt.checkpw(plainPassword,hashedPassword)
}