package com.example.shoppinglist.ui.ui_elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shoppinglist.domain.model.Item
import com.example.shoppinglist.ui.components.ShoppingListItem

@Composable
fun ItemList(
    items: List<Item>,
    onDeleteItem: (Item) -> Unit,
    onItemChecked: (Item, Boolean) -> Unit,
    onQuantityChange: (Item, Int) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            ShoppingListItem(
                item = item,
                onDeleteClick = { onDeleteItem(item) },
                onCheckedChange = { checked -> onItemChecked(item, checked) },
                onQuantityChange = { newQuantity -> onQuantityChange(item, newQuantity) }
            )
        }
    }
}