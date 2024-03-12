package com.ephirium.coffee.features.compliment.routes

import com.ephirium.coffee.data.requests.createCompliment
import com.ephirium.coffee.features.compliment.CreateComplimentReceiveRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.createComplimentRoute
    get() = post("/create-compliment") {
        val receive = try{
            call.receive<CreateComplimentReceiveRemote>()
        } catch (e: ContentTransformationException){
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if(createCompliment(compliment = receive.compliment)){
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }