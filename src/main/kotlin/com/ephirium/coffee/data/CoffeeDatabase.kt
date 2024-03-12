package com.ephirium.coffee.data

import org.litote.kmongo.reactivestreams.*
import org.litote.kmongo.coroutine.*

internal val client = KMongo.createClient().coroutine

internal val database = client.getDatabase("CoffeeDatabase")
