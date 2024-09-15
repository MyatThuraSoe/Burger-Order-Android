package com.example.glorytaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.glorytaste.data.MenuItem
import com.example.glorytaste.repository.CartRepository
import com.squareup.picasso.Picasso

class MenuAdapter(private val cartItemChangedListener: OnCartItemChangedListener) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private var menuItems = listOf<MenuItem>()

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuItemImage: ImageView = itemView.findViewById(R.id.menuItemImage)
        val menuItemName: TextView = itemView.findViewById(R.id.menuItemName)
        val menuItemPrice: TextView = itemView.findViewById(R.id.menuItemPrice)
        val increaseButton: Button = itemView.findViewById(R.id.increaseButton)
        val decreaseButton: Button = itemView.findViewById(R.id.decreaseButton)
        val quantityText: TextView = itemView.findViewById(R.id.quantityText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item_card, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.menuItemName.text = menuItem.name
        holder.menuItemPrice.text = "$${menuItem.price}"
        Picasso.get().load(menuItem.img).into(holder.menuItemImage)

        // Set initial quantity
        var quantity = CartRepository.getItemQuantity(menuItem.id)
        holder.quantityText.text = quantity.toString()

        // Increase quantity
        holder.increaseButton.setOnClickListener {
            quantity++
            holder.quantityText.text = quantity.toString()
            CartRepository.addToCart(menuItem, quantity)
            cartItemChangedListener.onCartItemChanged() // Notify fragment
        }

        // Decrease quantity
        holder.decreaseButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                holder.quantityText.text = quantity.toString()
                CartRepository.addToCart(menuItem, quantity)
                cartItemChangedListener.onCartItemChanged() // Notify fragment
            }
        }
    }

    override fun getItemCount() = menuItems.size

    fun setMenuItems(items: List<MenuItem>) {
        menuItems = items
        notifyDataSetChanged()
    }
    interface OnCartItemChangedListener {
        fun onCartItemChanged()
    }
}
