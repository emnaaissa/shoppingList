package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.data.repositories.ItemRepository
import com.example.shoppinglist.domain.model.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return repository.getAllItems()
    }
}