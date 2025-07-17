package db.tables

import org.jetbrains.exposed.dao.id.IntIdTable

const val MAX_VARCHAR_LENGTH = 100

object UserTable : IntIdTable() {
    val userName = varchar(name = "userName", length = MAX_VARCHAR_LENGTH).uniqueIndex()
    val password = varchar(name = "password", length = MAX_VARCHAR_LENGTH)
}
