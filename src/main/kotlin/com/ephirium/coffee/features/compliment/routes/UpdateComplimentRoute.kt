package com.ephirium.coffee.features.compliment.routes

import com.ephirium.coffee.data.requests.updateCompliment
import com.ephirium.coffee.features.compliment.UpdateComplimentReceiveRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.updateComplimentRoute
    get() = post("/update-compliment") {
        val receive = try{
            call.receive<UpdateComplimentReceiveRemote>()
        } catch (e: ContentTransformationException){
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        if(updateCompliment(compliment = receive.compliment)){
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }