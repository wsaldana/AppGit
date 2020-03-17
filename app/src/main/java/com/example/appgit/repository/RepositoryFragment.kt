package com.example.appgit.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgit.Adapter
import com.example.appgit.databinding.FragmentRepositoryBinding
import com.example.appgit.network.GitRepo
import com.example.appgit.R


class RepositoryFragment : Fragment() {

    private lateinit var viewModel: RepositoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRepositoryBinding>(inflater,
            R.layout.fragment_repository,container,false)

        //binding.recycler.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)


        val repositorio = viewModel.selectedRepo
        val manager = LinearLayoutManager(context)
        binding.recycler.layoutManager = manager

        repositorio.observe(this, Observer<List<GitRepo>>{
            Toast.makeText(context,repositorio.value.toString(),Toast.LENGTH_SHORT).show()
            //binding.recycler.adapter = Adapter(repositorio.value!!)
            val adapter = Adapter(repositorio.value!!)
            binding.recycler.adapter = adapter
        })


        return inflater.inflate(R.layout.fragment_repository, container, false)
    }
}
