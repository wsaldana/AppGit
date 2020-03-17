package com.example.appgit.search

import android.app.Application
import android.content.Context
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

    private var gitUser = MutableLiveData<GitUser>()
    val selectedUser: LiveData<GitUser>
        get() = gitUser

    init{
        //getGitUser()
        Log.i("REQUEST", "*************************************************")
    }

    fun getGitUser(name: String, cont: Context?){
        GitApi.retrofitService.getUser(name).enqueue(object:Callback<GitUser> {
            override fun onResponse(call: Call<GitUser>, response: Response<GitUser>) {
                if(response.isSuccessful){
                    gitUser.value = response?.body()
                    Toast.makeText(cont, response.body().toString(), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(cont, "No se encontro al usuario", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<GitUser>, t: Throwable) {
                t?.printStackTrace()
                Toast.makeText(cont, "No se encontro al usuario", Toast.LENGTH_SHORT).show()
            }
        })
    }
}