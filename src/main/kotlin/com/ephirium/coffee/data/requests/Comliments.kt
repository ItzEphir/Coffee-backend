package com.ephirium.coffee.data.requests

import com.ephirium.coffee.data.database
import com.ephirium.coffee.data.model.Compliment
import com.mongodb.client.model.Aggregates.sample
import org.litote.kmongo.coroutine.aggregate

private val compliments = database.getCollection<Compliment>()

suspend fun getCompliments(): List<Compliment> = compliments.aggregate<Compliment>(sample(1000)).toList()

suspend fun getComplimentForId(id: String): Compliment? = compliments.findOneById(id)

suspend fun createCompliment(compliment: Compliment): Boolean{
    val complimentNotExists = compliments.findOneById(compliment.id) == null
    return complimentNotExists && compliments.insertOne(compliment).wasAcknowledged()
}

suspend fun updateCompliment(compliment: Compliment): Boolean{
    val complimentExists = compliments.findOneById(compliment.id) != null
    return complimentExists && compliments.updateOneById(compliment.id, compliment).wasAcknowledged()
}

suspend fun deleteComplimentForId(id: String): Boolean = compliments.findOneById(id)?.let {
    compliments.deleteOneById(it.id).wasAcknowledged()
} ?: false
