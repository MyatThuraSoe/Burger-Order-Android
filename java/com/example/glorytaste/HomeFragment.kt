package com.example.glorytaste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.widget.TextView
import androidx.cardview.widget.CardView

import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find the "See All Items" TextView
        val seeAllItemsTextView: TextView = view.findViewById(R.id.txt_see_menu)
        // Set a click listener on "See All Items" TextView
        seeAllItemsTextView.setOnClickListener {
            navigateToMenuFragment()
        }

        // Example: Find the CardView for a specific menu item
        val menuCardView: CardView = view.findViewById(R.id.homeMenuCard) // Replace with the actual ID in fragment_home.xml
        // Set a click listener on the CardView
        menuCardView.setOnClickListener {
            navigateToMenuFragment()
        }

        return view
    }

    private fun navigateToMenuFragment() {
        // Use the Navigation component to navigate to MenuFragment
        val action = HomeFragmentDirections.actionHomeFragmentToMenuFragment()
        findNavController().navigate(action)
    }
}
