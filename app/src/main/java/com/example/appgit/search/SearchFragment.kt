package com.example.appgit.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.appgit.R
import com.example.appgit.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    //private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater,
            R.layout.fragment_search,container,false)

        binding.btnRepositorio.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_searchFragment_to_repositoryFragment)
        }

        return binding.root
    }
}
