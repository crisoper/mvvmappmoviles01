package com.example.mvvmapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ProductItemBinding
import com.example.mvvmapp.model.Product

class ProductAdapter(
    private val onEditClick: (Int) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.textNombre.text = product.nombre ?: "Sin nombre"
            binding.textPrecio.text = "$${product.precio ?: 0.0}"

            // 🔽 Cargar la imagen con Glide
            Glide.with(binding.root.context)
                .load(product.imagen)
                .placeholder(R.drawable.ic_launcher_background) // opcional
                .error(R.drawable.ic_launcher_foreground) // opcional
                .into(binding.imageProduct)

            binding.btnEditar.setOnClickListener {
                onEditClick(product.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}