package com.example.ongsomosmas.Fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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



        binding.btRegisterButton.setOnClickListener {
            DialogFragment(getString(R.string.bodyError)).show(childFragmentManager, DialogFragment.TAG)
        /*  viewModel.registerUser(Register(binding.tiNameLastname.editText?.text.toString(),binding.tiEmail.editText?.text.toString(),binding.tiPassword.editText?.text.toString()))

            viewModel.success.observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    println("Entro en el IF")
                } else {
                    println("Entro en el Else")
                }
            }
            viewModel.error.observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    DialogFragment(getString(R.string.bodyError)).show(childFragmentManager, DialogFragment.TAG)
                    clearText()
                }
            }
        */}

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_signUp_to_login)
            println("entro al callback")
        }

        return binding.root
    }

    private fun clearText(){
        binding.tiPassword.editText?.text?.clear()
        binding.tiEmail.editText?.text?.clear()
        binding.tiPassword.editText?.text?.clear()
        binding.tiRepeatPassword.editText?.text?.clear()
    }

}