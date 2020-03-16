package com.example.appgit.network

import com.squareup.moshi.Json

data class GitUser(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val userName: String,
    val repository: String
)