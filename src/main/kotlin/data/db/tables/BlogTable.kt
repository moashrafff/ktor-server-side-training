package com.moashraf.data.db.tables

import com.moashraf.data.db.tables.UserTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

const val MAX_VARCHAR_LENGTH = 100

object BlogTable : IntIdTable("blogs") {
    val title = varchar(name = "title", length = MAX_VARCHAR_LENGTH)
    val content = text(name = "content")

    val author = reference(name = "author_id", foreign = UserTable, onDelete = ReferenceOption.CASCADE)
}