package com.moashraf

import com.moashraf.db.initDb
import com.moashraf.di.configKoin
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configKoin()
    initDb()
}
