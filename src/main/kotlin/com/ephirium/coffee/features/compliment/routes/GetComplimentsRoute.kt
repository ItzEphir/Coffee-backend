package com.ephirium.coffee.features.compliment.routes

import com.ephirium.coffee.data.requests.getCompliments
import com.ephirium.coffee.features.compliment.GetComplimentsResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal val Routing.getComplimentsRoute get() = get("/get-compliments") {
    val compliments = getCompliments()
    call.respond(
        HttpStatusCode.OK,
        GetComplimentsResponseRemote(compliments = compliments)
    )
}