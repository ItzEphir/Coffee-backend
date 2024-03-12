package com.ephirium.coffee

import com.ephirium.coffee.features.compliment.configureComplimentRouting
import com.ephirium.coffee.features.login.configureLoginRouting
import com.ephirium.coffee.features.register.configureRegisterRouting
import com.ephirium.coffee.features.user.configureUserRouting
import com.ephirium.coffee.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureComplimentRouting()
    configureUserRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
