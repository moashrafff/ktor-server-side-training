package com.moashraf.data.db.entity

import com.moashraf.data.db.tables.BlogTable
import com.moashraf.domain.model.blog.Blog
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BlogEntity(id : EntityID<Int>) : IntEntity(id = id) {
    companion object : IntEntityClass<BlogEntity>(BlogTable)

    var title by BlogTable.title
    var content by BlogTable.content
    var author by UserEntity referencedOn BlogTable.author

    fun toBlog() : Blog = Blog(id = id.value, title = title, content = content, authorId = author.id.value)
}