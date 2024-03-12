package com.ephirium.coffee.features.user.routes

import com.ephirium.coffee.data.requests.getUserForId
import com.ephirium.coffee.features.user.GetUserReceiveRemote
import com.ephirium.coffee.features.user.GetUserResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.getUserRoute
    get() = get("/get-user") {
        val receive = try {
            call.receive<GetUserReceiveRemote>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val user = getUserForId(receive.id)
        user?.let {
            call.respond(HttpStatusCode.OK, GetUserResponseRemote(user = it))
        } ?: call.respond(HttpStatusCode.NotFound, "User Not Found")
    }