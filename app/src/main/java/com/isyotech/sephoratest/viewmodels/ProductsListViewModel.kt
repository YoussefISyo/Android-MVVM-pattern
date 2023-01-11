package com.isyotech.sephoratest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isyotech.sephoratest.data.models.Product
import com.isyotech.sephoratest.data.repositories.ProductsListRepository
import kotlinx.coroutines.*

class ProductsListViewModel constructor(private val productsListRepository: ProductsListRepository) :
    ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val productsList = MutableLiveData<List<Product>>()
    val productObject = MutableLiveData<Product>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = productsListRepository.getAllProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    productsList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun sendProduct(product: Product) {
        productObject.value = product
    }

}