package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivitySignUpBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentSignUp : Fragment() {

    private lateinit var binding : ActivitySignUpBinding
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { VideModelFactory() }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ActivitySignUpBinding.inflate(inflater, container, false)
        val fragmentContext = container?.context

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        binding.btRedirectLogin.setOnClickListener {
            findNavController().navigateUp()
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
                //Hacer algo
            }
            viewModel.error.observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    FragmentDialog(getString(R.string.bodyError), fragmentContext).show(
                        childFragmentManager,
                        FragmentDialog.TAG
                    )
                    clearTextSignUp()
                    alertError()
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

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
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

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
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

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
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

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {

                    binding.tiRepeatPassword.error = ""
                }

            })


        }

        return binding.root
    }
        fun clearTextSignUp() {
            binding.tiNameLastname.editText?.text?.clear()
            binding.tiEmail.editText?.text?.clear()
            binding.tiPassword.editText?.text?.clear()
            binding.tiRepeatPassword.editText?.text?.clear()
        }


        fun alertError() {
            binding.tiNameLastname.error = "Error Last Name"
            binding.tiEmail.error = "Error Email"
            binding.tiPassword.error = "Error Pass"
            binding.tiRepeatPassword.error = "Error repeat Pass"
        }

}