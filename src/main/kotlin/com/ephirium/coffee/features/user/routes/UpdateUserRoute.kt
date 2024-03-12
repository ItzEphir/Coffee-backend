package com.ephirium.coffee.features.user.routes

import com.ephirium.coffee.data.requests.updateUser
import com.ephirium.coffee.features.user.UpdateUserReceiveRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.updateUserRoute
    get() = post("/update-user") {
        val receive = try {
            call.receive<UpdateUserReceiveRemote>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (updateUser(receive.user)) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }