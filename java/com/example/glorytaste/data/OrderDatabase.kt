package com.example.glorytaste.data


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Order::class, CartItem::class], version = 1)
@TypeConverters(CartItemConverter::class)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}
