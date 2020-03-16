package com.example.appgit.search

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Callback
import com.example.appgit.network.GitApi
import com.example.appgit.network.GitUser
import retrofit2.Call
import retrofit2.Response

class SearchViewModel:ViewModel(){

    private val gitUser = MutableLiveData<GitUser>()
    val selectedUser: LiveData<GitUser>
        get() = gitUser

    init{
        getGitUser()
    }

    private fun getGitUser() {
        var gitUser: GitUser?
        GitApi.retrofitService.getUser("wsaldana").enqueue(object:Callback<GitUser> {
            override fun onResponse(call: Call<GitUser>, response: Response<GitUser>?) {
                gitUser = response?.body()
                Log.i("REQUEST", response?.body().toString())
            }

            override fun onFailure(call: Call<GitUser>, t: Throwable) {
                t?.printStackTrace()
                //Toast.makeText(this, "No se encontro al usuario", Toast.LENGTH_SHORT).show()
            }
        })
    }
}