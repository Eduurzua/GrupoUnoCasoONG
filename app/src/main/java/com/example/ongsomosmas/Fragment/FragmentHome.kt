package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.FragmentHomeBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { VideModelFactory(requireContext()) }
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        this.setHasOptionsMenu(true)

        binding = ActivityHomeBinding.inflate(inflater, container, false)

        /*Visibilidades de menu*/
        binding.MenuButton.setOnClickListener() {
            binding.menu.visibility = View.VISIBLE
            binding.MenuButton.visibility = View.GONE
        }
        binding.iconClear.setOnClickListener() {
            binding.menu.visibility = View.GONE
            binding.MenuButton.visibility = View.VISIBLE
        }

        /*Opciones menu*/
        binding.iconContact.setOnClickListener() {
            Toast.makeText(context, "Redirigiendo a contactos", Toast.LENGTH_SHORT).show()
        }
        binding.iconNews.setOnClickListener() {
            Toast.makeText(context, "Redirigiendo a novedades", Toast.LENGTH_SHORT).show()
        }
        binding.iconStaff.setOnClickListener() {
            Toast.makeText(context, "Redirigiendo a Staff", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }
}