package com.example.glorytaste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glorytaste.data.OrderDatabase
import kotlinx.coroutines.launch

class OrderHistoryFragment : Fragment() {

    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order_history, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.orderRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize OrderAdapter
        orderAdapter = OrderAdapter()
        recyclerView.adapter = orderAdapter

        // Load order history
        loadOrderHistory()

        // Change this to ImageView as per the layout
        val goToHome: ImageView = view.findViewById(R.id.historyBackButton)
        goToHome.setOnClickListener {
            val action = OrderHistoryFragmentDirections.actionOrderHistoryFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        return view
    }

    private fun loadOrderHistory() {
        lifecycleScope.launch {
            val orderList = OrderDatabase.getInstance(requireContext()).orderDao().getAllOrders()
            orderAdapter.submitList(orderList)
        }
    }
}
