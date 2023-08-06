package com.example.avmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.avmproject.databinding.ActivityStoreDetailBinding
import com.example.avmproject.databinding.StoreListItemBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoreDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityStoreDetailBinding
    private val viewModel:StoreDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val store = intent.getParcelableExtra<Store>(MainActivity.STORE)

        store?.let {
            binding.ivStoreIcon.load(it.imageUrl)
            binding.tvStoreName.text = it.name
            binding.tvStoreDescription.text = it.description
            binding.tvStoreBlock.text = it.block
            binding.tvStoreFloor.text = it.floor

            binding.ivBackArrow.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            binding.btnDeleteStore.setOnClickListener {

                viewModel.deleteStore(store)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

}