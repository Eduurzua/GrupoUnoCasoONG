package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.FragmentHomeBinding


class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.setHasOptionsMenu(true)

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finish()
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

        /*Opciones menu*/
        binding.iconContact.setOnClickListener() {
            findNavController().navigate(R.id.action_home_to_contact)
        }
        binding.iconNews.setOnClickListener() {
            findNavController().navigate(R.id.action_home_to_news)
        }
        binding.iconStaff.setOnClickListener() {
            Toast.makeText(context, "Redirigiendo a Staff", Toast.LENGTH_SHORT).show()
        }

        /*ojo como vuelve hacia atrás cuando esté implementado*/

        return binding.root
    }
}