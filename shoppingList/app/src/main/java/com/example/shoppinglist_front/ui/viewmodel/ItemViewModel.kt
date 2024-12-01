package com.example.shoppinglist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.domain.model.Item
import com.example.shoppinglist.domain.usecases.AddItemUseCase
import com.example.shoppinglist.domain.usecases.DeleteItemUseCase
import com.example.shoppinglist.domain.usecases.GetAllItemsUseCase
import com.example.shoppinglist.domain.usecases.UpdateItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val addItemUseCase: AddItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase
) : ViewModel() {

    val items: Flow<List<Item>> = getAllItemsUseCase()

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            addItemUseCase(Item(name = name, quantity = quantity))
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            deleteItemUseCase(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            updateItemUseCase(item)
        }
    }
}