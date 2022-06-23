package com.example.ongsomosmas.Fragment

import android.os.Bundle
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

        viewModel.getNews(4)

        viewModel.news.observe(viewLifecycleOwner){value ->
            if(value!= null){
                listUrl.clear()
                listUrl.add(value[0].image.replace("http://","https://"))
                listUrl.add(value[1].image.replace("http://","https://"))
                listUrl.add(value[2].image.replace("http://","https://"))
                listUrl.add(value[3].image.replace("http://","https://"))
                viewAdapter.notifyDataSetChanged()
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                viewModel.selectNew(position)
                    super.onPageSelected(position)
            }
        })



        viewModel.new.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                binding.etTitleNews.text = value.name
                binding.etDetails.text = value.content
            }
        }

        viewAdapter = SliderViewAdapter(listUrl)
        viewPager.adapter = viewAdapter
        binding.cIndicator.setViewPager(viewPager)
        viewAdapter.registerAdapterDataObserver(binding.cIndicator.adapterDataObserver)
        viewPager.setPageTransformer(ZoomOutPageTransformer())


        return binding.root

        }
}