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

    private val _addstoreState: MutableStateFlow<AddStoreState> = MutableStateFlow(AddStoreState.Idle)
    val addstoreState: StateFlow<AddStoreState> = _addstoreState


init {
    getAddStoreList()
}


    fun AddStore(store: Store) {                                                   //listeye ekleme kısmı
        viewModelScope.launch {
            //    val storeAdded = Database.stores[Database.stores.indexOf(store)]
            _addstoreState.value =AddStoreState.Add(store)
            Database.stores.add(store)
            delay(2000)
            _addstoreState.value = AddStoreState.Intent
        }
    }

    fun getAddStoreList() {                                                        //listeyi gösterme kısmı
        viewModelScope.launch {
            _storeImage.value = GetAddStoreState.Result(Database.addStoreList)
        }
    }
}