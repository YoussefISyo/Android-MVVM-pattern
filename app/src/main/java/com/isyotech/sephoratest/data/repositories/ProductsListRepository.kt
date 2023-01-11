package com.isyotech.sephoratest.data.repositories

import com.isyotech.sephoratest.data.network.RetrofitService

class ProductsListRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllProducts() = retrofitService.getAllProducts()
}