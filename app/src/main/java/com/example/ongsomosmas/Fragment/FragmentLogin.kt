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

    val result: Boolean = false

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


        binding.btnLogin.setOnClickListener {
            if (validate()) {
                viewModel.loginUser(
                    Login(
                        binding.tiEmail.editText?.text.toString(),
                        binding.tiPassword.editText?.text.toString()
                    )
                )

                viewModel.success.observe(viewLifecycleOwner) { response ->
                    //TO DO
                }
                viewModel.error.observe(viewLifecycleOwner) { response ->
                    if (response != null) {
                        FragmentDialog(getString(R.string.loginError), fragmentContext).show(
                            childFragmentManager,
                            FragmentDialog.TAG
                        )
                    }
                }
            } else {
                Toast.makeText(context, "Error de login", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }

    private fun validate(): Boolean {
        val result = arrayOf(validateEmail(), validatePassword())
        return false !in result
    }

    private fun validateEmail(): Boolean {
        val email = binding.tiEmail.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.tiEmail.error = "Campo email no puede estar vacío"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tiEmail.error = "Campo email inválido"
            false
        } else {
            binding.tiEmail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.tiPassword.editText?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +          //al menos 1 dígito
                    "(?=.*[A-Z])" +          //al menos una minúscula
                    "(?=.*[@#$%^&+=!])" +    //al menos un simbolo especial
                    "(?=\\S+$)" +            //sin espacios
                    ".{8,}" +                //minimo 8 de largo
                    "$"
        )
        return if (password.isEmpty()) {
            binding.tiPassword.error = "Campo contraseña no puede estar vacío"
            false
        } else if (!passwordRegex.matcher(password).matches()) {
            binding.tiPassword.error = "La contraseña es inválida"
            false
        } else {
            binding.tiPassword.error = null
            true
        }
    }
}