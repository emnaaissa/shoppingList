package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.data.repositories.ItemRepository
import com.example.shoppinglist.domain.model.Item
import javax.inject.Inject

class UpdateItemUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(item: Item) {
        repository.updateItem(item)
    }
}