package com.example.appgit.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.appgit.R
import com.example.appgit.databinding.FragmentSearchBinding
import com.example.appgit.network.GitApi
import com.example.appgit.network.GitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater,
            R.layout.fragment_search,container,false)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        binding.btnRepositorio.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_searchFragment_to_repositoryFragment)
        }
        binding.btnBuscar.setOnClickListener {
            getGitUser()
        }

        return binding.root
    }

    private fun getGitUser() {
        var gitUser: GitUser?
        GitApi.retrofitService.getUser("wsaldana").enqueue(object: Callback<GitUser> {
            override fun onResponse(call: Call<GitUser>, response: Response<GitUser>?) {
                //gitUser = response?.body()
                Log.i("REQUEST", response?.body().toString())
            }

            override fun onFailure(call: Call<GitUser>, t: Throwable) {
                t?.printStackTrace()
                Toast.makeText(context, "No se encontro al usuario", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
