package com.ephirium.coffee.features.register

import com.ephirium.coffee.cache.InMemoryCache
import com.ephirium.coffee.cache.TokenCache
import com.ephirium.coffee.data.model.User
import com.ephirium.coffee.data.requests.createUser
import com.ephirium.coffee.utils.generateToken
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() =
    routing {
        post("/register") {
            val receive = call.receive<RegisterReceiveRemote>()

            if (InMemoryCache.registerList.contains(receive)) {
                call.respond(HttpStatusCode.Conflict, "Register already exists")
                return@post
            }

            val token = generateToken(receive.login, receive.password)
            if (!createUser(User(login = receive.login, name = receive.name, passwordToken = token))) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }
            InMemoryCache.registerList.add(receive)
            InMemoryCache.tokens.emit(
                InMemoryCache.tokens.value.toMutableList()
                    .apply { add(TokenCache(login = receive.login, token = token)) }
            )
            call.respond(HttpStatusCode.OK, RegisterResponseRemote(token = token))
        }
    }
