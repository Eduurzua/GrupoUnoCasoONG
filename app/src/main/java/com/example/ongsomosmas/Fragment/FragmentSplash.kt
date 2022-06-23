package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.R
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentSplash : Fragment() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.myLooper()!!).postDelayed({
            if(viewModel.loadUser()){
                println("viewModel.loadUser()")
                findNavController().navigate(R.id.action_splash_to_home)
                println("Me fui al home")
            }else{
                println("viewModel.loadUser()")
                findNavController().navigate(R.id.action_splash_to_login)
                println("Me fui al login")
            }
        },5000)
        return view
    }



}
