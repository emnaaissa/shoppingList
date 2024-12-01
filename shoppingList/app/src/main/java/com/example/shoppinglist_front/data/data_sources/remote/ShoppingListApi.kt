package com.example.shoppinglist.data.remote

import retrofit2.http.*

interface ShoppingListApi {
    @GET("api/items")
    suspend fun getAllItems(): List<ItemDto>

    @POST("api/items")
    suspend fun addItem(@Body item: ItemDto)

    @PUT("api/items/{id}")
    suspend fun updateItem(@Path("id") id: Int, @Body item: ItemDto)

    @DELETE("api/items/{id}")
    suspend fun deleteItem(@Path("id") id: Int)
}