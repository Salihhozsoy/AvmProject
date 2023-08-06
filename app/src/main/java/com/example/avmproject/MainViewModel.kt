package com.example.avmproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    private val _storeListState: MutableStateFlow<StoreListState> = MutableStateFlow(StoreListState.Idle)
    val storeListState: StateFlow<StoreListState> = _storeListState

    init {
        getStores()
    }

   private fun getStores() {
        viewModelScope.launch {
            _storeListState.value = StoreListState.Result(Database.stores)
        }
    }
}