package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ongsomosmas.databinding.FragmentNewsBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentNews : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { VideModelFactory(requireContext()) }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)


            return binding.root
        }
}