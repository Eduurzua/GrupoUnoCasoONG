package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.ActivityLoginBinding


class FragmentLogin : Fragment() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ActivityLoginBinding.inflate(inflater, container,false)

        binding.btRedirectLogin.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signUp)
        }
        return binding.root
    }
}