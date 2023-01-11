package com.isyotech.sephoratest.data.models

import com.google.gson.annotations.SerializedName

data class Product(
    val product_id: Int,
    val product_name: String,
    val description: String,
    @SerializedName("images_url")
    val imageUrl: ImagesUrl,
    val price: Double,
)