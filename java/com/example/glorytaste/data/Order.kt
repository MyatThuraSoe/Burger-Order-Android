package com.example.glorytaste.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val address: String,
    val phone: String,
    val session: String,
    val totalCost: String,
    val date: Long = System.currentTimeMillis(), // Store timezone as timestamp
    @TypeConverters(CartItemConverter::class)
    val cart: List<CartItem>
)
