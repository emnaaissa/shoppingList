package com.example.shoppinglist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}