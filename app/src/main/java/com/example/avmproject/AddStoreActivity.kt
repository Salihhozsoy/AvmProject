package com.example.avmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avmproject.databinding.ActivityAddStoreBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddStoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddStoreBinding
    private val viewModel: AddStoreViewModel by viewModels()

    private var addStoreUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackk.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        observeShowAddStore()
        observeAddStore()
        binding.btnAddStore.setOnClickListener {
            val id = binding.etId.text.toString()
            val name = binding.etName.text.toString()
            val block = binding.etBlock.text.toString()
            val floor = binding.etFloor.text.toString()
            val description = binding.etDescription.text.toString()

            val store = Store(id.toInt(), name, addStoreUrl!!, block, floor, description)
            viewModel.AddStore(store)
        }

    }

   private fun observeAddStore() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addstoreState.collect {
                    when (it) {
                        is AddStoreState.Idle -> {}
                        is AddStoreState.Intent -> {
                            val intent = Intent(this@AddStoreActivity, MainActivity::class.java)
                            startActivity(intent)
                        }

                        is AddStoreState.Add -> {
                         //   Toast.makeText(this@AddStoreActivity)
                        }
                    }
                }
            }
        }
    }

   private fun observeShowAddStore() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.storeImage.collect {
                    when (it) {
                        is GetAddStoreState.Idle -> {}
                        is GetAddStoreState.Loading -> {}
                        is GetAddStoreState.Result -> {
                            binding.rvAddStore.adapter = AddStoreAdapter(this@AddStoreActivity, Database.addStoreList) {
                                addStoreUrl = it
                            }
                        }
                    }
                }
            }
        }
    }
}