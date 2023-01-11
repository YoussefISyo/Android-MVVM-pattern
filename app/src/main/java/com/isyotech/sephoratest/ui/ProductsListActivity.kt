package com.isyotech.sephoratest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.isyotech.sephoratest.R
import com.isyotech.sephoratest.data.network.RetrofitService
import com.isyotech.sephoratest.data.repositories.ProductsListRepository
import com.isyotech.sephoratest.viewmodels.MyViewModelFactory
import com.isyotech.sephoratest.viewmodels.ProductsListViewModel


class ProductsListActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductsListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitService = RetrofitService.getInstance()
        val allProductsRepository = ProductsListRepository(retrofitService)
        viewModel = ViewModelProvider(this, MyViewModelFactory(allProductsRepository))[ProductsListViewModel::class.java]
    }
}