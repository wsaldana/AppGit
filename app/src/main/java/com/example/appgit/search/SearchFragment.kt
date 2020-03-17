package com.example.appgit.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.appgit.R
import com.example.appgit.databinding.FragmentSearchBinding
import com.example.appgit.network.GitUser

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
            binding.btnRepositorio.visibility = View.VISIBLE
        }

        return binding.root
    }
}
