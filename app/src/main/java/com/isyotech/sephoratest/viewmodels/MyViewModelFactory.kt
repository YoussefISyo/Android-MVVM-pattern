package com.isyotech.sephoratest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isyotech.sephoratest.data.repositories.ProductsListRepository

class MyViewModelFactory constructor(private val repository: ProductsListRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductsListViewModel::class.java)) {
            ProductsListViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}