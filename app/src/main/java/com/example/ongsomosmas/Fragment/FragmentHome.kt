package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ongsomosmas.databinding.ActivityHomeBinding


class FragmentHome : Fragment() {

    private lateinit var binding: ActivityHomeBinding

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

        /*ojo como vuelve hacia atrás cuando esté implementado*/

        return binding.root
    }
}