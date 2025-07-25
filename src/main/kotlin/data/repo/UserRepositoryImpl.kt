package com.moashraf.data.repo

import com.moashraf.data.db.entity.UserEntity
import com.moashraf.data.db.utils.dbQuery
import com.moashraf.domain.model.user.request.LoginRequest
import com.moashraf.domain.model.user.request.RegisterRequest
import com.moashraf.domain.model.user.User
import com.moashraf.domain.repo.UserRepository
import com.moashraf.data.db.tables.UserTable
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl : UserRepository {
    override suspend fun createUser(registerRequest: RegisterRequest): User? {
        return dbQuery {
            val user = UserEntity.find { UserTable.userName eq registerRequest.userName }.firstOrNull()
            if (user != null) {
                return@dbQuery null
            }
            val newUser = UserEntity.new {
                this.userName = registerRequest.userName
                this.password = hashPassword(registerRequest.password)
            }
            return@dbQuery newUser.toUser()
        }
    }

    override suspend fun getUserById(id: Int): User? {
        return dbQuery {
            UserEntity.findById(id)?.toUser()
        }
    }

    override suspend fun authenticate(request: LoginRequest): User? {
        return dbQuery {
            val user = UserEntity.find { UserTable.userName eq request.userName }.firstOrNull()

            user?.takeIf { userEntity ->
                verifyPassword(plainPassword = request.password , hashedPassword = userEntity.password )
            }?.let {
                User(id = user.id.value, userName = user.userName)
            }
        }
    }

    override fun hashPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())


    override fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean =
        BCrypt.checkpw(plainPassword,hashedPassword)
}