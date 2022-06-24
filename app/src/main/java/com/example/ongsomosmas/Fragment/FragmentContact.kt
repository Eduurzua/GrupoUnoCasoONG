package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ongsomosmas.Model.PostMessage
import com.example.ongsomosmas.R
import com.example.ongsomosmas.databinding.FragmentContactBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
        binding.tiMessage.editText?.addTextChangedListener(object : TextWatcher {
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

                binding.tiMessage.error = ""

            }

        })

        viewModel.postMessage.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                Toast.makeText(context, "$value", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                dialogAlert(getString(R.string.bodyErrorContact))
            }
        }

        return binding.root
    }

    private fun clearTextSignUp() {
        binding.tiNameLastname.editText?.text?.clear()
        binding.tiEmail.editText?.text?.clear()
        binding.tiMessage.editText?.text?.clear()
    }

    private fun warnError() {
        binding.tiMessage.error = (getString(R.string.titleError))
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

}