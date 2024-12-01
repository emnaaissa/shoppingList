package com.example.shoppinglist.di

import android.app.Application
import androidx.room.Room
import com.example.shoppinglist.data.local.ShoppingListDatabase
import com.example.shoppinglist.data.remote.ShoppingListApi
import com.example.shoppinglist.data.repositories.ItemRepository
import com.example.shoppinglist.data.repositories.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingListDatabase(app: Application): ShoppingListDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingListDatabase::class.java,
            "shoppingList.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingListApi(): ShoppingListApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:9093/") // Android emulator localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShoppingListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(
        api: ShoppingListApi,
        db: ShoppingListDatabase
    ): ItemRepository {
        return ItemRepositoryImpl(api, db.itemDao())
    }
}