package com.isyotech.sephoratest.data.utilities

import com.isyotech.sephoratest.data.models.Product

object ValidationUtil {

    fun validateProduct(product: Product) : Boolean {
        if (product.product_name.isNotEmpty()) {
            return true
        }
        return false
    }
}