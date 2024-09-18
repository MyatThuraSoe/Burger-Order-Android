package com.example.glorytaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.glorytaste.data.Order
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var orderList: List<Order> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.bind(order)
    }

    override fun getItemCount() = orderList.size

    fun submitList(orders: List<Order>) {
        orderList = orders
        notifyDataSetChanged()
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val orderIdTextView: TextView = itemView.findViewById(R.id.textView3)
        private val orderNameTextView: TextView = itemView.findViewById(R.id.textView5)
        private val totalCostTextView: TextView = itemView.findViewById(R.id.textView7)
        private val orderTimeTextView: TextView = itemView.findViewById(R.id.textView9)

        fun bind(order: Order) {
            // Set order details
            orderIdTextView.text = order.id.toString()
            orderNameTextView.text = order.name
            totalCostTextView.text = "${order.totalCost} $"

            // Format the date from timestamp to a readable string
            val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(order.date.toLong()))
            orderTimeTextView.text = formattedDate
        }
    }
}
