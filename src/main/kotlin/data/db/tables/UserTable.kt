package com.moashraf.data.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable


object UserTable : IntIdTable() {
    val userName = varchar(name = "userName", length = MAX_VARCHAR_LENGTH).uniqueIndex()
    val password = varchar(name = "password", length = MAX_VARCHAR_LENGTH)
}
