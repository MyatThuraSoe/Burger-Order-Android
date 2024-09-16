package com.example.glorytaste.data


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartItemConverter {

    @TypeConverter
    fun fromCartItemList(cartItems: List<CartItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.toJson(cartItems, type)
    }

    @TypeConverter
    fun toCartItemList(cartItemString: String): List<CartItem> {
        val gson = Gson()
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.fromJson(cartItemString, type)
    }
}
