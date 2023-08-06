package com.example.avmproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StoreDetailViewModel:ViewModel() {

    fun deleteStore(store:Store){
        viewModelScope.launch {
            val storeDelete = Database.stores[Database.stores.indexOf(store)]

            Database.stores.remove(storeDelete)
        }
    }
}