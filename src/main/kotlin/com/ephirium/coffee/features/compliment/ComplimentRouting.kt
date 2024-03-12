package com.ephirium.coffee.features.compliment

import com.ephirium.coffee.features.compliment.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureComplimentRouting() =
    routing {
        getComplimentsRoute
        getComplimentRoute
        createComplimentRoute
        updateComplimentRoute
        deleteComplimentRoute
    }