package com.moashraf.di

import di.reposModule
import di.servicesModule
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configKoin() {
    install(Koin){
        slf4jLogger()
        modules(listOf(reposModule,servicesModule))
    }
}

