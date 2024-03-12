package com.ephirium.coffee.features.login

import com.ephirium.coffee.cache.InMemoryCache
import com.ephirium.coffee.data.requests.getUserForLogin
import com.ephirium.coffee.utils.generateToken
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() = routing {
    post("/login") {
        val receive = call.receive<LoginReceiveRemote>()

        val isCached = InMemoryCache.registerList.map { it.login }.contains(receive.login)
        if (isCached) {
            val token = InMemoryCache.tokens.value.first { it.login == receive.login }.token
            if (token == generateToken(receive.login, receive.password)) {
                call.respond(HttpStatusCode.OK, LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.Conflict, "Token not pass")
            }
            return@post
        }

        val user = getUserForLogin(receive.login)

        if (user == null) {
            call.respond(HttpStatusCode.Conflict, "User not exists")
            return@post
        }

        val token = generateToken(receive.login, receive.password)
        if (token == user.passwordToken) {
            call.respond(HttpStatusCode.OK, LoginResponseRemote(token = token))
        } else {
            call.respond(HttpStatusCode.Conflict, "Token not pass")
        }
    }
}
