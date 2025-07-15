package com.moashraf.db

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initDb() {
    // All the configuration variables in application.yaml
    val url = environment.config.property("database.url").getString()
    val driver = environment.config.property("database.driver").getString()
    val user = environment.config.property("database.user").getString()
    // This variable should be added to the environment variables
    val password = System.getenv().get("ktor_moashrafff_db_password")

    val db = Database.connect(
        url = url,
        driver = driver,
        user = user
    )

    transaction(db = db) {

    }
}