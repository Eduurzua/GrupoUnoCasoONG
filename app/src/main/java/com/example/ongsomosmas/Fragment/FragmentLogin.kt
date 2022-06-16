package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import java.util.regex.Pattern


class FragmentLogin : Fragment() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory() })
    private val loginViewModel: LogInViewModel by viewModels()

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
        binding.tiEmail.addOnEditTextAttachedListener  {
            loginViewModel.validate(email, password)
        }
        binding.tiPassword.addOnEditTextAttachedListener  {
            loginViewModel.validate(email, password)
        }
        /*observando viewmodel para bloquear o desbloquear boton*/
        loginViewModel.enableButton.observe(viewLifecycleOwner){ value ->
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
                        DialogFragment(getString(R.string.loginError), fragmentContext).show(
                            childFragmentManager,
                            DialogFragment.TAG
                        )
                    }
                }
        }
        return binding.root
    }
}