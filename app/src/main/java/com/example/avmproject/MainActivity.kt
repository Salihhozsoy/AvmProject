package com.example.avmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avmproject.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: StoreAdapter
    private val viewModel: MainViewModel by viewModels()

    companion object {
        const val STORE = "store"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeStoreList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.menu_option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuExit-> finish()
            R.id.menuAdd -> {
                val intent =Intent(this,AddStoreActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun observeStoreList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.storeListState.collect {
                    when (it) {
                        is StoreListState.Idle -> {}
                        is StoreListState.Result -> {
                            adapter = StoreAdapter(this@MainActivity, it.stores) { store, position ->
                                    val intent = Intent(this@MainActivity, StoreDetailActivity::class.java)
                                    intent.putExtra(STORE, store)
                                    startActivity(intent)
                                }
                            binding.rvStore.adapter = adapter
                        }
                    }
                }
            }
        }
    }
}