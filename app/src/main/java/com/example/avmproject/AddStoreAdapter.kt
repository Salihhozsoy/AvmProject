package com.example.avmproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.example.avmproject.databinding.AddListItemBinding

class AddStoreAdapter(
    private val context: Context,
    private val addStores: List<AddStore>,
    val onClick: (addStoreImageUrl:String) -> Unit
) : RecyclerView.Adapter<AddStoreAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: AddListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivAddImage = binding.ivAddImage
        val ivCheck =binding.ivCheck
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStoreAdapter.CustomViewHolder {
        val binding = AddListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddStoreAdapter.CustomViewHolder, position: Int) {
        val addStores = addStores[position]

        holder.ivAddImage.load(addStores.addStoreImage)

        holder.itemView.setOnClickListener {
            onClick(addStores.addStoreImage)
            holder.ivCheck.isVisible =true
        }

    }

    override fun getItemCount(): Int {
        return addStores.size
    }


}