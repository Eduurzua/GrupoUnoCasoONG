package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding


class FragmentLogin : Fragment() {

    private lateinit var binding : ActivityLoginBinding

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ActivityLoginBinding.inflate(inflater, container,false)

        binding.btSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signUp)
        }

        /*el boton comienza desactivado*/
        /*binding.btnLogin.isEnabled = false*/

        binding.btnLogin.setOnClickListener {
            Toast.makeText(context, "boton login apretado", Toast.LENGTH_SHORT).show()
            println("mensaje")
        }

        return binding.root

    }
}