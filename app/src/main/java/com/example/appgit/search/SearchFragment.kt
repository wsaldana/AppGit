package com.example.appgit.search

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.appgit.R
import com.example.appgit.databinding.FragmentSearchBinding
import com.example.appgit.network.GitApi
import com.example.appgit.network.GitUser
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater,
            R.layout.fragment_search,container,false)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val usuario: LiveData<GitUser> = viewModel.selectedUser

        usuario.observe(this, Observer<GitUser>{
                binding.textView.text = usuario.value?.login
        })

        binding.btnRepositorio.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_searchFragment_to_repositoryFragment)
        }

        binding.btnBuscar.setOnClickListener {
            viewModel.getGitUser(binding.editText.text.toString(), context)
        }

        return binding.root
    }
}
