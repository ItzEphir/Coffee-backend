package com.ephirium.coffee.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val login: String,
    val name: String,
    val passwordToken: String
)