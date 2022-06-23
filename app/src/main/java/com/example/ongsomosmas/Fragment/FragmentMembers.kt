package com.example.ongsomosmas.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.FragmentContactBinding
import com.example.ongsomosmas.databinding.FragmentMembersBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentMembers :  Fragment() {

    private lateinit var binding: FragmentMembersBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMembersBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

/*Visibilidades de menu*/
        binding.MenuButton.setOnClickListener() {
            binding.menu.visibility = View.VISIBLE
            binding.MenuButton.visibility = View.GONE
        }
        binding.iconClear.setOnClickListener() {
            binding.menu.visibility = View.GONE
            binding.MenuButton.visibility = View.VISIBLE
        }

        binding.button.setOnClickListener() {
            binding.etLastNam.text = viewModel.findUser()
        }

        /*Opciones menu*/
        binding.iconHome.setOnClickListener() {
            findNavController().navigate(R.id.action_members_to_home)
        }
        binding.iconNews.setOnClickListener() {
            findNavController().navigate(R.id.action_members_to_news)
        }
        binding.iconContact.setOnClickListener() {
            findNavController().navigate(R.id.action_members_to_contact)
        }
    viewModel.getMembers(4)

        return binding.root
    }
}