package com.example.ongsomosmas.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FragmentLogin : Fragment() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityLoginBinding.inflate(inflater, container, false)

        binding.btSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_contact)
        }
        /*Boton login inactivo*/
        binding.btnLogin.isEnabled = false
        /*observando campos email y password en caso de cambios*/
        binding.tiEmail.editText?.addTextChangedListener {
            viewModel.validate(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString()
            )
        }
        binding.tiPassword.editText?.addTextChangedListener {
            viewModel.validate(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString()
            )
        }
        /*observando viewmodel para bloquear o desbloquear boton*/
        viewModel.enableButton.observe(viewLifecycleOwner) { value ->
            binding.btnLogin.isEnabled = value
        }

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                Login(
                    binding.tiEmail.editText?.text.toString(),
                    binding.tiPassword.editText?.text.toString()
                )
            )
            viewModel.success.observe(viewLifecycleOwner) { response ->
                println("response observe  : " + response )
                if (response) {
                    Toast.makeText(context, "Logeado", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.error.observe(viewLifecycleOwner) {response ->
                println("response observe error  : " + response )
                if (response != null) {
                    dialogAlert(getString(R.string.bodyErrorLogin))

                }
            }

        }
        binding.tiEmail.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {

                binding.tiEmail.error = ""

            }

        })
        binding.tiPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {

                binding.tiPassword.error = ""

            }

        })

        return binding.root
    }

    private fun clearTextLogin() {
        binding.tiEmail.editText?.text?.clear()
        binding.tiPassword.editText?.text?.clear()
    }


    private fun warnErrorLogin() {
        binding.tiEmail.error = (getString(R.string.wrongEmail))
        binding.tiPassword.error = (getString(R.string.wrongPassword))
    }

    private fun dialogAlert(body: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.titleError))
                .setMessage(body)
                .setPositiveButton(
                    getString(R.string.buttonOk)
                ) { _, _ ->
                    clearTextLogin()
                    warnErrorLogin()
                }
                .show()
        };
    }
}