package com.isyotech.sephoratest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.isyotech.sephoratest.data.network.RetrofitService
import com.isyotech.sephoratest.data.repositories.ProductsListRepository
import com.isyotech.sephoratest.databinding.FragmentProductsBinding
import com.isyotech.sephoratest.viewmodels.MyViewModelFactory
import com.isyotech.sephoratest.viewmodels.ProductsListViewModel

class ProductsFragment : Fragment() {

    lateinit var viewModel: ProductsListViewModel
    lateinit var binding: FragmentProductsBinding
    lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val retrofitService = RetrofitService.getInstance()
        val allProductsRepository = ProductsListRepository(retrofitService)
        viewModel = activity?.run {
            ViewModelProvider(this, MyViewModelFactory(allProductsRepository))[ProductsListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        adapter = ProductsAdapter(viewModel)

        binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.recyclerview.adapter = adapter

        viewModel.productsList.observe(viewLifecycleOwner,{
            adapter.setProducts(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllProducts()

        return binding.getRoot()
    }

}