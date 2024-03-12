package com.ephirium.coffee.features.user

import com.ephirium.coffee.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class GetUserReceiveRemote(
    val id: String
)

@Serializable
data class GetUserResponseRemote(
    val user: User
)

@Serializable
data class UpdateUserReceiveRemote(
    val user: User
)

@Serializable
data class DeleteUserReceiveRemote(
    val id: String
)