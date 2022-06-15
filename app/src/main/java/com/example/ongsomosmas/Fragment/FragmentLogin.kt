package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory


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

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                Login(
                    binding.tiEmail.editText?.text.toString(),
                    binding.tiPassword.editText?.text.toString()
                )
            )
            viewModel.success.observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    println("IF  : response")
                } else {
                    println("Else  : response")
                }
            }
            viewModel.error.observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    DialogFragment(getString(R.string.loginError), fragmentContext).show(
                        childFragmentManager,
                        DialogFragment.TAG
                    )
                    viewModel.clearTextLogin()
                }
            }
        }
        return binding.root
    }
}