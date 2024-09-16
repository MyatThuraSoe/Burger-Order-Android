package com.example.glorytaste

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.glorytaste.api.OrderRequest
import com.example.glorytaste.api.RetrofitInstance
import com.example.glorytaste.data.CartItem
import com.example.glorytaste.data.MenuItem
import com.example.glorytaste.data.Order
import com.example.glorytaste.repository.CartRepository
import kotlinx.coroutines.launch

class OrderFragment : Fragment() {

    private lateinit var cartItems: Array<MenuItem>
    private var totalAmount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cartItems = OrderFragmentArgs.fromBundle(it).cartItems
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalCost = cartItems.sumByDouble { it.price.toDouble() * CartRepository.getItemQuantity(it.id) }
        val deliveryFee = 0.0
        totalAmount = totalCost + deliveryFee

        view.findViewById<TextView>(R.id.order_txt_cost).text = "$totalCost $"
        view.findViewById<TextView>(R.id.order_delivery_fee).text = "$deliveryFee $"
        view.findViewById<TextView>(R.id.order_total_amount).text = "$totalAmount $"

        view.findViewById<Button>(R.id.orderButton).setOnClickListener {
            submitOrder()
        }
    }

//    private fun submitOrder() {
//        // Show progress bar
//        view?.findViewById<ProgressBar>(R.id.order_progressbar)?.visibility = View.VISIBLE
//
//        val userName = view?.findViewById<EditText>(R.id.order_input_name)?.text.toString()
//        val userEmail = view?.findViewById<EditText>(R.id.order_input_email)?.text.toString()
//        val userPhone = view?.findViewById<EditText>(R.id.order_input_phone)?.text.toString()
//        val userAddress = view?.findViewById<EditText>(R.id.order_input_address)?.text.toString()
//
//        val orderData = Order(
//            name = userName,
//            email = userEmail,
//            address = userAddress,
//            phone = userPhone,
//            session = "someSessionId", // Add appropriate session ID
//            cart = cartItems.map {
//                CartItem(
//                    itemId = it.id,
//                    name = it.name,
//                    quantity = CartRepository.getItemQuantity(it.id),
//                    price = it.price.toDouble()
//                )
//            },
//            totalCost = totalAmount.toString()
//        )
//
//        // Send orderData to the server and store it in the local database
//        lifecycleScope.launch {
//            try {
//                val response = RetrofitInstance.orderApiService.postOrder(orderData)
//                // Hide progress bar
//                view?.findViewById<ProgressBar>(R.id.order_progressbar)?.visibility = View.GONE
//                if (response.isSuccessful) {
//                    // Show success alert
//                    showAlert("Order submitted successfully!")
//                    // Navigate to HomeFragment
//                    findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHomeFragment())
//                } else {
//                    showAlert("Failed to submit order!")
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                // Hide progress bar
//                view?.findViewById<ProgressBar>(R.id.order_progressbar)?.visibility = View.GONE
//                showAlert("Error occurred while submitting order!")
//            }
//        }
//    }
    private fun submitOrder() {
        // Show progress bar
        view?.findViewById<ProgressBar>(R.id.order_progressbar)?.visibility = View.VISIBLE

        val userName = view?.findViewById<EditText>(R.id.order_input_name)?.text.toString()
        val userEmail = view?.findViewById<EditText>(R.id.order_input_email)?.text.toString()
        val userPhone = view?.findViewById<EditText>(R.id.order_input_phone)?.text.toString()
        val userAddress = view?.findViewById<EditText>(R.id.order_input_address)?.text.toString()

        val orderData = Order(
            name = userName,
            email = userEmail,
            address = userAddress,
            phone = userPhone,
            session = "someSessionId", // Add appropriate session ID
            cart = cartItems.map {
                CartItem(
                    itemId = it.id,
                    name = it.name,
                    quantity = CartRepository.getItemQuantity(it.id),
                    price = it.price.toDouble()
                )
            },
            totalCost = totalAmount.toString()
        )

        // Wrap orderData in OrderRequest
        val orderRequest = OrderRequest(orderData)

        // Send orderData to the server and store it in the local database
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.orderApiService.postOrder(orderRequest)
                // Hide progress bar
                view?.findViewById<ProgressBar>(R.id.order_progressbar)?.visibility = View.GONE

                if (response.isSuccessful) {
                    // Show success alert
                    showAlert("Order submitted successfully!")
                    // Navigate to HomeFragment
                    findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHomeFragment())
                } else {
                    // Log error details
                    Log.e("OrderFragment", "Failed to submit order: ${response.code()} - ${response.message()}")
                    showAlert("Failed to submit order!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Hide progress bar
                view?.findViewById<ProgressBar>(R.id.order_progressbar)?.visibility = View.GONE
                showAlert("Error occurred while submitting order!")
            }
        }
    }



    private fun showAlert(message: String) {
        // Display alert message
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
