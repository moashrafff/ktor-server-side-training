package com.moashraf.db.entity

import com.moashraf.domain.model.User
import db.tables.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id : EntityID<Int>) : IntEntity(id = id) {
    companion object : IntEntityClass<UserEntity>(UserTable)

    var userName by UserTable.userName
    var password by UserTable.password

    fun toUser() : User = User(id = id.value, userName = userName)
}