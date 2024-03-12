package com.ephirium.coffee.features.compliment.routes

import com.ephirium.coffee.data.requests.deleteComplimentForId
import com.ephirium.coffee.features.compliment.DeleteComplimentReceiveRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.deleteComplimentRoute
    get() = post("/delete-compliment") {
        val receive = try {
            call.receive<DeleteComplimentReceiveRemote>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (deleteComplimentForId(id = receive.id)) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }