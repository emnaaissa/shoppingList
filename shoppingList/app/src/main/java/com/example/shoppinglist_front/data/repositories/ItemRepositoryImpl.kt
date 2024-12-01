package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.local.ItemDao
import com.example.shoppinglist.data.local.ItemEntity
import com.example.shoppinglist.data.remote.ShoppingListApi
import com.example.shoppinglist.data.remote.ItemDto
import com.example.shoppinglist.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepositoryImpl(
    private val api: ShoppingListApi,
    private val dao: ItemDao
) : ItemRepository {
    override fun getAllItems(): Flow<List<Item>> {
        return dao.getAllItems().map { entities ->
            entities.map { it.toItem() }
        }
    }

    override suspend fun addItem(item: Item) {
        withContext(Dispatchers.IO) {
            try {
                val dto = item.toDto()
                api.addItem(dto)
                dao.insertItem(item.toEntity())
            } catch (e: Exception) {
                dao.insertItem(item.toEntity())
            }
        }
    }

    override suspend fun updateItem(item: Item) {
        withContext(Dispatchers.IO) {
            try {
                val dto = item.toDto()
                api.updateItem(item.id, dto)
                dao.insertItem(item.toEntity())
            } catch (e: Exception) {
                dao.insertItem(item.toEntity())
            }
        }
    }

    override suspend fun deleteItem(item: Item) {
        withContext(Dispatchers.IO) {
            try {
                api.deleteItem(item.id)
                dao.deleteItem(item.toEntity())
            } catch (e: Exception) {
                dao.deleteItem(item.toEntity())
            }
        }
    }

    private fun ItemEntity.toItem(): Item {
        return Item(id, name, quantity, isChecked)
    }

    private fun Item.toEntity(): ItemEntity {
        return ItemEntity(id, name, quantity, isChecked)
    }

    private fun Item.toDto(): ItemDto {
        return ItemDto(id, name, quantity, isChecked)
    }
}