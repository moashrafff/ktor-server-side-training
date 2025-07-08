package com.moashraf.db

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.initDb() {
    val url = environment.config.property("database.url").getString()
    val driver = environment.config.property("database.driver").getString()
    val user = environment.config.property("database.user").getString()
    val password = System.getenv().get("ktor_moashrafff_db_password")

    val db = Database.connect(
        url = url,
        driver = driver,
        user = user
    )
}