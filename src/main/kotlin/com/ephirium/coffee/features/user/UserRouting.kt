package com.ephirium.coffee.features.user

import com.ephirium.coffee.features.user.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureUserRouting() =
    routing {
        getUserRoute
        updateUserRoute
        deleteUserRoute
    }
