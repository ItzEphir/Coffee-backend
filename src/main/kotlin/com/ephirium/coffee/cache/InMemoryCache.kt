package com.ephirium.coffee.cache

import com.ephirium.coffee.data.model.Compliment
import com.ephirium.coffee.data.model.User
import com.ephirium.coffee.features.compliment.GetComplimentReceiveRemote
import com.ephirium.coffee.features.register.RegisterReceiveRemote
import com.ephirium.coffee.features.user.GetUserReceiveRemote
import kotlinx.coroutines.flow.MutableStateFlow

data class TokenCache(
    val login: String,
    val token: String
)

object InMemoryCache {
    val registerList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val getUserList: MutableList<GetUserReceiveRemote> = mutableListOf()
    val getComplimentList: MutableList<GetComplimentReceiveRemote> = mutableListOf()

    val tokens: MutableStateFlow<List<TokenCache>> = MutableStateFlow(emptyList())
    val users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val compliments: MutableStateFlow<List<Compliment>> = MutableStateFlow(emptyList())
}