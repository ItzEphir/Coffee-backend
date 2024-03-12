package com.ephirium.coffee.features.compliment.routes

import com.ephirium.coffee.data.requests.getComplimentForId
import com.ephirium.coffee.features.compliment.GetComplimentReceiveRemote
import com.ephirium.coffee.features.compliment.GetComplimentResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.getComplimentRoute
    get() = get("/get-compliment") {
        val receive = try {
            call.receive<GetComplimentReceiveRemote>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val compliment = getComplimentForId(id = receive.id)
        compliment?.let {
            call.respond(
                HttpStatusCode.OK,
                GetComplimentResponseRemote(compliment = it)
            )
        } ?: call.respond(
            HttpStatusCode.NotFound,
            "Compliment not found"
        )
    }