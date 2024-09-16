package com.example.glorytaste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glorytaste.api.RetrofitInstance
import com.example.glorytaste.repository.CartRepository
import kotlinx.coroutines.launch

class MenuFragment : Fragment(), MenuAdapter.OnCartItemChangedListener {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var totalAmountTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Initialize RecyclerView and Adapter
        val menuRecyclerView: RecyclerView = view.findViewById(R.id.menuRecyclerView)
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        menuAdapter = MenuAdapter(this) // Pass the fragment as the listener
        menuRecyclerView.adapter = menuAdapter

        // Initialize total amount TextView
        totalAmountTextView = view.findViewById(R.id.totalAmountInMenu)

        // Fetch and display menu items
        fetchMenuItems()

        // Example: Find the CardView for a specific menu item
        val backButton: ImageView = view.findViewById(R.id.menuBackButton)
        backButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        // Go to CartFragment
        val goToCart: TextView = view.findViewById(R.id.totalAmountInMenu)
        goToCart.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToCartFragment()
            findNavController().navigate(action)
        }

        return view
    }

    private fun fetchMenuItems() {
        lifecycleScope.launch {
            try {
                val menuItems = RetrofitInstance.menuApiService.getMenuItems()
                menuAdapter.setMenuItems(menuItems)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Update total amount when the cart changes
    override fun onCartItemChanged() {
        updateTotalAmount()
    }

    // Method to update total amount
    private fun updateTotalAmount() {
        val total = CartRepository.getTotalAmount()
        totalAmountTextView.text = "Total: $$total"
    }
}
