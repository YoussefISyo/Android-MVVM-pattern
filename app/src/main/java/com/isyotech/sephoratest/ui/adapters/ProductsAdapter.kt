package com.isyotech.sephoratest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isyotech.sephoratest.R
import com.isyotech.sephoratest.data.models.Product
import com.isyotech.sephoratest.data.utilities.ValidationUtil
import com.isyotech.sephoratest.databinding.AdapterProductBinding
import com.isyotech.sephoratest.viewmodels.ProductsListViewModel

class ProductsAdapter(private val viewModel: ProductsListViewModel) : RecyclerView.Adapter<MainViewHolder>() {

    var productsList = mutableListOf<Product>()

    fun setProducts(movies: List<Product>) {
        this.productsList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProductBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val product = productsList[position]
        if (ValidationUtil.validateProduct(product)) {
            holder.binding.name.text = product.product_name
            Glide.with(holder.itemView.context).load(product.imageUrl.small).into(holder.binding.imageview)
        }


        holder.binding.productCard.setOnClickListener {
            viewModel.sendProduct(product)
            Navigation.findNavController(holder.itemView).navigate(R.id.action_productsFragment_to_detailsFragment)
        }

    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}

class MainViewHolder(val binding: AdapterProductBinding) : RecyclerView.ViewHolder(binding.root) {

}