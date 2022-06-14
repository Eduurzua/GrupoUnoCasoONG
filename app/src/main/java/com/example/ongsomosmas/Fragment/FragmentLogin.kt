package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory


class FragmentLogin : Fragment() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { VideModelFactory() }
    )

    private val loginViewModel : LoginViewModel by viewModels()

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

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(Login(binding.tiEmail.editText?.text.toString(),binding.tiPassword.editText?.text.toString()))
            //Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        }

        viewModel.login.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                println("Entro en el IF  : $value")
            } else {
                println("Entro en el Else  : $value")
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                Toast.makeText(context,value, Toast.LENGTH_LONG).show()
            }
        }
        return binding.root

    }
}