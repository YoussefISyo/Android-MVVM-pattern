package com.isyotech.sephoratest.data.utilities

import com.isyotech.sephoratest.data.models.Product

object ValidationUtil {

    //add image url verification
    fun validateProduct(product: Product) : Boolean {
        if (product.product_name.isNotEmpty() || product.imageUrl.small.isNotEmpty()) {
            return true
        }
        return false
    }
}