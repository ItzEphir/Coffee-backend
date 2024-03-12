package com.ephirium.coffee.data.requests

import com.ephirium.coffee.data.database
import com.ephirium.coffee.data.model.User
import org.litote.kmongo.eq

private val users = database.getCollection<User>()

suspend fun getUserForId(id: String): User? = users.findOneById(id)

suspend fun getUserForLogin(login: String): User? = users.findOne(User::login eq login)

suspend fun createUser(user: User): Boolean =
    if (users.findOneById(user.id) == null) users.insertOne(user).wasAcknowledged() else false

suspend fun updateUser(user: User): Boolean =
    if (users.findOneById(user.id) != null) users.updateOneById(user.id, user).wasAcknowledged() else false

suspend fun deleteUserForId(id: String): Boolean = users.findOneById(id)?.let {
    users.deleteOneById(it.id).wasAcknowledged()
} ?: false
