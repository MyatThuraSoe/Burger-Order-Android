package com.example.glorytaste


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glorytaste.R
import com.example.glorytaste.repository.CartRepository
import com.example.glorytaste.MenuAdapter
import com.example.glorytaste.data.MenuItem

class CartFragment : Fragment(), MenuAdapter.OnCartItemChangedListener {

    private lateinit var cartAdapter: MenuAdapter
    private lateinit var cashoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Initialize RecyclerView and Adapter
        val cartRecyclerView: RecyclerView = view.findViewById(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartAdapter = MenuAdapter(this)
        cartRecyclerView.adapter = cartAdapter

        // Load cart items into the adapter
        val cartItems = CartRepository.getCartItems()
        cartAdapter.setMenuItems(cartItems)

        // Initialize cashout button
        cashoutButton = view.findViewById(R.id.cashoutButton)
        updateTotalAmount()

        cashoutButton.setOnClickListener {
            navigateToCheckout()
        }
        val gobacktoMenu: ImageView = view.findViewById(R.id.cartBackButton)
        gobacktoMenu.setOnClickListener{
            val action = CartFragmentDirections.actionCartFragmentToMenuFragment()
            findNavController().navigate(action)
        }


        return view
    }

    // Method to update total amount
    override fun onCartItemChanged() {
        updateTotalAmount()
    }

    private fun updateTotalAmount() {
        val total = CartRepository.getTotalAmount()
        cashoutButton.text = "Cashout ($$total)"
    }

    private fun navigateToCheckout() {
        val cartItems = CartRepository.getCartItems().toTypedArray()
        val action = CartFragmentDirections.actionCartFragmentToOrderFragment(cartItems)
        findNavController().navigate(action)
    }


}
