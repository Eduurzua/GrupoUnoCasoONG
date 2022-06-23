package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Dto.PostMessage
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.FragmentContactBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentContact : Fragment() {

    private lateinit var binding: FragmentContactBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        /*Visibilidades de menu*/
        binding.MenuButton.setOnClickListener() {
            binding.menu.visibility = View.VISIBLE
            binding.MenuButton.visibility = View.GONE
        }
        binding.iconClear.setOnClickListener() {
            binding.menu.visibility = View.GONE
            binding.MenuButton.visibility = View.VISIBLE
        }

        binding.etLastNam.text = viewModel.findUser()

        /*Opciones menu*/
        binding.icoHome.setOnClickListener() {
            findNavController().navigate(R.id.action_contact_to_news)
        }
        binding.iconNews.setOnClickListener() {
            findNavController().navigate(R.id.action_contact_to_news)
        }
        binding.iconStaff.setOnClickListener() {
            findNavController().navigate(R.id.action_contact_to_members)
        }

        /*Boton enviar mensaje inactivo*/
        binding.btSendMessage.isEnabled = false

        /*observando campos nombre y apellido, email y mensaje en caso de cambios*/
        binding.tiNameLastname.editText?.addTextChangedListener {
            viewModel.validateContact(
                binding.tiNameLastname.editText?.text.toString() ,
                binding.tiEmail.editText?.text.toString(),
                binding.tiMessage.editText?.text.toString()
            )
        }
        binding.tiEmail.editText?.addTextChangedListener {
            viewModel.validateContact(
                binding.tiNameLastname.editText?.text.toString() ,
                binding.tiEmail.editText?.text.toString(),
                binding.tiMessage.editText?.text.toString()
            )
        }
        binding.tiMessage.editText?.addTextChangedListener {
            viewModel.validateContact(
                binding.tiNameLastname.editText?.text.toString() ,
                binding.tiEmail.editText?.text.toString(),
                binding.tiMessage.editText?.text.toString()
            )
        }

        /*observando viewmodel para bloquear o desbloquear boton*/
        viewModel.enableButton.observe(viewLifecycleOwner) { value ->
            binding.btSendMessage.isEnabled = value
        }

        binding.btSendMessage.setOnClickListener(){
            viewModel.postMessage(
                PostMessage(
                    1,
                    binding.tiNameLastname.editText?.text.toString(),
                    binding.tiEmail.editText?.text.toString(),
                    " ",
                    binding.tiMessage.editText?.text.toString(),
                    " ",
                    " ",
                    " "

                )
            )
        }

        viewModel.postMessage.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                Toast.makeText(context, "$value", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                Toast.makeText(context, "$value", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}