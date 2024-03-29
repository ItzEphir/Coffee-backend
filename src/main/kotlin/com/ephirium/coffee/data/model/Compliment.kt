package com.ephirium.coffee.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Compliment(
    @BsonId
    val id: String = ObjectId().toString(),
    val language: String,
    val text: String
)