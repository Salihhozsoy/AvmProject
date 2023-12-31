package com.example.avmproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddStoreViewModel : ViewModel() {

    private val _storeImage: MutableStateFlow<GetAddStoreState> = MutableStateFlow(GetAddStoreState.Idle)
    val storeImage: StateFlow<GetAddStoreState> = _storeImage

    private val _addStoreState: MutableStateFlow<AddStoreState> = MutableStateFlow(AddStoreState.Idle)
    val addStoreState: StateFlow<AddStoreState> = _addStoreState


init {
    getAddStoreList()
}


    fun addStore(store: Store) {                                                   //listeye ekleme kısmı
        viewModelScope.launch {
            _addStoreState.value =AddStoreState.Add(store)
            Database.stores.add(store)
            delay(2000)
            _addStoreState.value = AddStoreState.Intent
        }
    }

    private fun getAddStoreList() {                                                        //listeyi gösterme kısmı
        viewModelScope.launch {
            _storeImage.value = GetAddStoreState.Result(Database.addStoreList)
        }
    }
}