package com.example.glorytaste.repository

import com.example.glorytaste.data.MenuItem

object CartRepository {
    private val cartItems = mutableMapOf<String, Int>() // item ID to quantity
    private val menuItemsMap = mutableMapOf<String, MenuItem>() // Store items for quick lookup

    // Add the item to the cart
    fun addToCart(item: MenuItem, quantity: Int) {
        if (quantity > 0) {
            cartItems[item.id] = quantity
            menuItemsMap[item.id] = item // Store the item for later use
        } else {
            cartItems.remove(item.id)
            menuItemsMap.remove(item.id)
        }
    }

    // Get the quantity of a specific item in the cart
    fun getItemQuantity(itemId: String): Int {
        return cartItems[itemId] ?: 0
    }

    // Get all items currently in the cart
    fun getCartItems(): List<MenuItem> {
        return menuItemsMap.values.toList()
    }

    // Calculate the total amount in the cart
    fun getTotalAmount(): Double {
        var total = 0.0
        for ((id, quantity) in cartItems) {
            val item = menuItemsMap[id]
            if (item != null) {
                total += item.price.toDouble() * quantity
            }
        }
        return total
    }
}
