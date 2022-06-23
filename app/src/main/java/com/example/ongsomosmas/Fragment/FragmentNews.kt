package com.example.ongsomosmas.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ongsomosmas.Dto.News
import com.example.ongsomosmas.R
import com.example.ongsomosmas.adapter.SliderViewAdapter
import com.example.ongsomosmas.databinding.FragmentNewsBinding
import com.example.ongsomosmas.views.MainViewModel
import com.example.ongsomosmas.views.VideModelFactory

class FragmentNews : Fragment() {

    private lateinit var viewAdapter : SliderViewAdapter
    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewPager : ViewPager2
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { VideModelFactory(requireContext()) }
    )

    private val listUrl : MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
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

        /*Opciones menu*/
        binding.iconContact.setOnClickListener() {
            findNavController().navigate(R.id.action_news_to_contact)
        }
        binding.iconHome.setOnClickListener() {
            findNavController().navigate(R.id.action_news_to_home)
        }
        binding.iconStaff.setOnClickListener() {
            findNavController().navigate(R.id.action_news_to_members)
        }

        /*viewModel.getNews(4)

        viewModel.news.observe(viewLifecycleOwner){value ->
            if(value!= null){
                listUrl.add(value.component1().image)
                listUrl.add(value.component2().image)
                listUrl.add(value.component3().image)
                listUrl.add(value.component4().image)
            }
        }


        viewAdapter = SliderViewAdapter(listUrl)
        viewPager.adapter = viewAdapter

         */

        return binding.root
        }
}