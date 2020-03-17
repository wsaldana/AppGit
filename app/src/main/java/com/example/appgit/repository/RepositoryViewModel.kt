package com.example.appgit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appgit.network.GitApi
import com.example.appgit.network.GitRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel: ViewModel(){

    private var gitRepo = MutableLiveData<List<GitRepo>>()
    val selectedRepo: LiveData<List<GitRepo>>
        get() = gitRepo

    init{
        getGitRepo("wsaldana")
        Log.i("REQUEST", "*************************************************")
    }

    fun getGitRepo(name: String){
        GitApi.retrofitService.getRepos(name).enqueue(object: Callback<List<GitRepo>> {
            override fun onResponse(call: Call<List<GitRepo>>, response: Response<List<GitRepo>>) {
                if(response.isSuccessful){
                    gitRepo.value = response?.body()
                }
            }
            override fun onFailure(call: Call<List<GitRepo>>, t: Throwable) {
                t?.printStackTrace()
            }
        })
    }
}