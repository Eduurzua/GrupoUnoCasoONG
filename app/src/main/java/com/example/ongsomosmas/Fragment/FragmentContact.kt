package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ongsomosmas.databinding.ActivityContactBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentContact : Fragment() {

    private lateinit var binding: ActivityContactBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityContactBinding.inflate(inflater, container, false)

        binding.btSendMessage.setOnClickListener {
            Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}