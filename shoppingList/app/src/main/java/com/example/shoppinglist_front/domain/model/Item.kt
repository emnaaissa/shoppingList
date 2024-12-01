package com.example.shoppinglist.domain.model

data class Item(
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val isChecked: Boolean = false
)