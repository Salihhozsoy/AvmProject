package com.example.avmproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.avmproject.databinding.StoreListItemBinding

class StoreAdapter(
    private val context: Context,
    private var stores: List<Store>,
    val onClick: (Store, position: Int) -> Unit
):RecyclerView.Adapter<StoreAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: StoreListItemBinding) : RecyclerView.ViewHolder(binding.root) {
       val ivStoreIcon =binding.ivStoreIcon
        val tvStoreName =binding.tvStoreName
        val tvStoreBlock =binding.tvStoreBlock
        val tvStoreFloor =binding.tvStoreFloor
        val tvStoreDescription =binding.tvStoreDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreAdapter.CustomViewHolder {
        val binding =StoreListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreAdapter.CustomViewHolder, position: Int) {
        val stores =stores[position]
        holder.itemView.setOnClickListener{
            onClick(stores,position)
        }
        holder.ivStoreIcon.load(stores.imageUrl)
        println(stores.imageUrl)
        holder.tvStoreName.text =stores.name
        holder.tvStoreBlock.text=stores.block
        holder.tvStoreFloor.text=stores.floor
        holder.tvStoreDescription.text=stores.description
    }

    override fun getItemCount(): Int {
        return stores.size
    }
}