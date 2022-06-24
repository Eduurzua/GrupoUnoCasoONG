package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ongsomosmas.R
import com.example.ongsomosmas.adapter.SliderViewAdapter
import com.example.ongsomosmas.adapter.SliderViewAdapterMembers
import com.example.ongsomosmas.databinding.FragmentMembersBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FragmentMembers :  Fragment() {

    private lateinit var binding: FragmentMembersBinding
    private lateinit var viewAdapter : SliderViewAdapterMembers
    private lateinit var viewPager : ViewPager2
    private val viewModel: MainViewModel by viewModels(factoryProducer = { VideModelFactory(this.requireContext()) })

    private val listUrlMembers : MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMembersBinding.inflate(inflater, container, false)
        viewPager = binding.vPagerHome

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

        viewModel.members.observe(viewLifecycleOwner){value ->
            if(value!= null){
                listUrlMembers.clear()
                listUrlMembers.add(value[0].image.replace("http://","https://"))
                listUrlMembers.add(value[1].image.replace("http://","https://"))
                listUrlMembers.add(value[2].image.replace("http://","https://"))
                listUrlMembers.add(value[3].image.replace("http://","https://"))
                viewAdapter.notifyDataSetChanged()
            }
        }

        viewModel.error.observe(viewLifecycleOwner){value ->
            if(value != null){
                dialogAlert(getString(R.string.newsNotOK))
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                viewModel.selectMember(position)
                super.onPageSelected(position)
            }
        })

        viewModel.member.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                Log.i("DEBUG",value.name)
                Log.i("DEBUG",value.description)
                binding.etTitleStaff.text = value.name
                binding.etDetailsStaff.text = value.description
                Log.i("DEBUG",binding.etTitleStaff.text.toString())
                Log.i("DEBUG",binding.etDetailsStaff.text.toString())
            }
        }

        viewAdapter = SliderViewAdapterMembers(listUrlMembers)
        viewPager.adapter = viewAdapter
        binding.cIndicator.setViewPager(viewPager)
        viewAdapter.registerAdapterDataObserver(binding.cIndicator.adapterDataObserver)
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        viewModel.getMembers(4)

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