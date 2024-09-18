package com.example.glorytaste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.glorytaste.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set click listeners using DataBinding
        binding.txtSeeMenu.setOnClickListener {
            navigateToMenuFragment()
        }

        binding.homeMenuCard.setOnClickListener {
            navigateToMenuFragment()
        }

        binding.homeToOrderHistory.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOrderHistoryFragment())
        }

        binding.homeCartCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCartFragment())
        }

        return view
    }

    private fun navigateToMenuFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToMenuFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
