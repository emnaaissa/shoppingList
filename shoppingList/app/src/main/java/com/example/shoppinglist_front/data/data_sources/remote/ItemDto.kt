package com.example.shoppinglist.data.remote

data class ItemDto(
    val id: Int,
    val name: String,
    val quantity: Int,
    val isChecked: Boolean
)