package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivitySignUpBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FragmentSignUp : Fragment() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { VideModelFactory(requireContext()) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivitySignUpBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        binding.btRedirectLogin.setOnClickListener {
            findNavController().navigateUp()
        }

        /*Boton login inactivo*/
        binding.btRegisterButton.isEnabled = false

        /*Validando cada campo del register*/
        binding.tiNameLastname.editText?.addTextChangedListener {
            viewModel.validateRegister(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString(),
                binding.tiRepeatPassword.editText?.text.toString(),
                binding.tiNameLastname.editText?.text.toString()
            )
        }
        binding.tiEmail.editText?.addTextChangedListener {
            viewModel.validateRegister(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString(),
                binding.tiRepeatPassword.editText?.text.toString(),
                binding.tiNameLastname.editText?.text.toString()
            )
        }
        binding.tiPassword.editText?.addTextChangedListener {
            viewModel.validateRegister(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString(),
                binding.tiRepeatPassword.editText?.text.toString(),
                binding.tiNameLastname.editText?.text.toString()
            )
        }

        binding.tiRepeatPassword.editText?.addTextChangedListener {
            viewModel.validateRegister(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString(),
                binding.tiRepeatPassword.editText?.text.toString(),
                binding.tiNameLastname.editText?.text.toString()
            )
        }

        /*observando viewmodel para bloquear o desbloquear boton*/
        viewModel.enableRegister.observe(viewLifecycleOwner) { value ->
            if (value) {
                binding.btRegisterButton.isEnabled = value
            }
        }

        binding.tiPassword.editText?.addTextChangedListener {
            viewModel.samePasswordRepeat(
                binding.tiPassword.editText?.text.toString().trim(),
                binding.tiRepeatPassword.editText?.text.toString().trim()
            )
        }

        binding.tiRepeatPassword.editText?.addTextChangedListener {
            viewModel.samePasswordRepeat(
                binding.tiPassword.editText?.text.toString().trim(),
                binding.tiRepeatPassword.editText?.text.toString().trim()
            )
        }

        /*observando password para que sean iguales*/
        viewModel.samePassword.observe(viewLifecycleOwner) { value ->
            if (value) {
                binding.tiRepeatPassword.error = ""
            } else binding.tiRepeatPassword.error = getString(R.string.notSamePassword)
        }

        binding.btRegisterButton.setOnClickListener {

            viewModel.registerUser(
                Register(
                    binding.tiNameLastname.editText?.text.toString(),
                    binding.tiEmail.editText?.text.toString(),
                    binding.tiPassword.editText?.text.toString()
                )
            )

            viewModel.success.observe(viewLifecycleOwner) { response ->
                println("Success Observe  : " + response)
                if (response) {
                    dialogAlertRegister(getString(R.string.bodyRegisterOK))
                }
            }
            viewModel.error.observe(viewLifecycleOwner) { response ->
                println("Error Observe  : " + response)
                if (response != null) {
                    dialogAlert(getString(R.string.bodyErrorRegister))


                }
            }
        }

        binding.tiNameLastname.editText?.addTextChangedListener(object : TextWatcher {
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

                binding.tiNameLastname.error = ""

            }

        })
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
        binding.tiRepeatPassword.editText?.addTextChangedListener(object : TextWatcher {
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

                binding.tiRepeatPassword.error = ""
            }

        })



        return binding.root
    }

    private fun clearTextSignUp() {
        binding.tiNameLastname.editText?.text?.clear()
        binding.tiEmail.editText?.text?.clear()
        binding.tiPassword.editText?.text?.clear()
        binding.tiRepeatPassword.editText?.text?.clear()
    }

    private fun warnError() {
        binding.tiNameLastname.error = (getString(R.string.wrongName))
        binding.tiEmail.error = (getString(R.string.wrongEmail))
        binding.tiPassword.error = (getString(R.string.wrongPassword))
        binding.tiRepeatPassword.error = (getString(R.string.wrongPasswordRepeat))
    }

    private fun dialogAlert(body: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.titleError))
                .setMessage(body)
                .setPositiveButton(
                    getString(R.string.buttonOk)
                ) { _, _ ->
                    clearTextSignUp()
                    warnError()
                }
                .show()
        };
    }

    private fun dialogAlertRegister(body: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.titleRegister))
                .setMessage(body)
                .setPositiveButton(
                    getString(R.string.buttonOk)
                ) { _, _ ->
                    findNavController().navigateUp()
                }
                .show()
        };
    }
}