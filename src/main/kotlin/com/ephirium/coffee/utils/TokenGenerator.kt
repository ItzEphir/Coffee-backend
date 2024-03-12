package com.ephirium.coffee.utils

fun generateToken(login: String, password: String): String = (login + password).hashCode().toString()
