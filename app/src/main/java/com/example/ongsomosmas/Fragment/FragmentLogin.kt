package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import java.util.regex.Pattern


class FragmentLogin : Fragment() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityLoginBinding.inflate(inflater, container, false)
        val fragmentContext = container?.context

        binding.btSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signUp)
        }

        val email = binding.tiEmail.editText?.text.toString()
        val password = binding.tiPassword.editText?.text.toString()

        /*Boton login inactivo*/
        binding.btnLogin.isEnabled = false
        /*observando campos email y password en caso de cambios*/
        binding.tiEmail.editText?.addTextChangedListener  {
            viewModel.validate(email, password)
        }
        binding.tiPassword.editText?.addTextChangedListener  {
            viewModel.validate(email, password)
        }
        /*observando viewmodel para bloquear o desbloquear boton*/
        viewModel.enableButton.observe(viewLifecycleOwner){ value ->
            binding.btnLogin.isEnabled = value
        }

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                Login(
                    binding.tiEmail.editText?.text.toString(),
                    binding.tiPassword.editText?.text.toString()
                )
            )
                viewModel.error.observe(viewLifecycleOwner) { response ->
                    if (response != null) {
                        FragmentDialog(getString(R.string.loginError), fragmentContext).show(
                            childFragmentManager,
                            FragmentDialog.TAG
                        )
                    }
                }
        }
        return binding.root
    }
}