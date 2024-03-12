package com.ephirium.coffee.features.user.routes

import com.ephirium.coffee.data.requests.deleteUserForId
import com.ephirium.coffee.features.user.DeleteUserReceiveRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.deleteUserRoute
    get() = post("/delete-user") {
        val receive = try {
            call.receive<DeleteUserReceiveRemote>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (deleteUserForId(receive.id)) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }