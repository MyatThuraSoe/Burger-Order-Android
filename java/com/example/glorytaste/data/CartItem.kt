package com.example.glorytaste.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemId: String,
    val name: String,
    val quantity: Int,
    val price: Double
)
