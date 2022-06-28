package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ongsomosmas.R
import com.example.ongsomosmas.adapter.RecyclerViewAdapterMembers
import com.example.ongsomosmas.databinding.FragmentMembersBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FragmentMembers :  Fragment() {

    private lateinit var binding: FragmentMembersBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })

    private val listUrlMembers : MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMembersBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        binding.etLastNam.text = viewModel.findUser()

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
        binding.iconHome.setOnClickListener() {
            findNavController().navigate(R.id.action_members_to_home)
        }
        binding.iconNews.setOnClickListener() {
            findNavController().navigate(R.id.action_members_to_news)
        }
        binding.iconContact.setOnClickListener() {
            findNavController().navigate(R.id.action_members_to_contact)
        }
        binding.rvHome.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        viewModel.getMembers(4)

        val adapter = RecyclerViewAdapterMembers(listUrlMembers,object : RecyclerViewAdapterMembers.MemberSelectionListener{
            override fun select(position: Int) {
                viewModel.selectMember(position)
            }
        })
        binding.rvHome.adapter = adapter

        viewModel.members.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                listUrlMembers.clear()
                listUrlMembers.add(value[0].image.replace("http://", "https://"))
                listUrlMembers.add(value[1].image.replace("http://", "https://"))
                listUrlMembers.add(value[2].image.replace("http://", "https://"))
                listUrlMembers.add(value[3].image.replace("http://", "https://"))
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.member.observe(viewLifecycleOwner) { value ->
            if (value != null) {

                binding.etTitleStaff.text = value.name
                binding.etDetailsStaff.text = value.description

                Glide.with(this)
                    .load(value.image.replace("http://", "https://"))
                    .fitCenter()
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivStaff)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                dialogAlert(getString(R.string.newsNotOK))
            }
        }

        return binding.root
    }

    private fun dialogAlert(body: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.titleError))
                .setMessage(body)
                .setPositiveButton(
                    getString(R.string.buttonOk)
                ) { _, _ ->
                }
                .show()
        };
    }

}