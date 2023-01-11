package com.isyotech.sephoratest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.isyotech.sephoratest.data.network.RetrofitService
import com.isyotech.sephoratest.data.repositories.ProductsListRepository
import com.isyotech.sephoratest.databinding.FragmentDetailsBinding
import com.isyotech.sephoratest.viewmodels.MyViewModelFactory
import com.isyotech.sephoratest.viewmodels.ProductsListViewModel

class DetailsFragment : Fragment() {

    lateinit var viewModel: ProductsListViewModel
    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val retrofitService = RetrofitService.getInstance()
        val allProductsRepository = ProductsListRepository(retrofitService)
        viewModel = activity?.run {
            ViewModelProvider(this, MyViewModelFactory(allProductsRepository))[ProductsListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel.productObject.observe(viewLifecycleOwner, {
            binding.title.text = it.product_name
            binding.desc.text = it.description
            Glide.with(this).load(it.imageUrl.small).into(binding.productImg)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}