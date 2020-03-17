package com.example.appgit.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface GitApiService {

    @GET("users/{username}")
    fun getUser(@Path("username") username:String): Call<GitUser>

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): Call<List<GitRepo>>
}

object GitApi {
    val retrofitService : GitApiService by lazy { retrofit.create(GitApiService::class.java) }
}