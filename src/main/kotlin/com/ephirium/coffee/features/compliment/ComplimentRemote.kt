package com.ephirium.coffee.features.compliment

import com.ephirium.coffee.data.model.Compliment
import kotlinx.serialization.Serializable

@Serializable
data class GetComplimentsResponseRemote(
    val compliments: List<Compliment>
)

@Serializable
data class GetComplimentReceiveRemote(
    val id: String
)

@Serializable
data class GetComplimentResponseRemote(
    val compliment: Compliment
)

@Serializable
data class CreateComplimentReceiveRemote(
    val compliment: Compliment
)

@Serializable
data class UpdateComplimentReceiveRemote(
    val compliment: Compliment
)

@Serializable
data class DeleteComplimentReceiveRemote(
    val id: String
)