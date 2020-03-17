package com.example.appgit.network

import com.squareup.moshi.Json
import java.util.*

data class GitUser(
    @Json(name = "avatar_url") val avatar: String?,
    val login: String,
    val id: String
)