package com.example.glorytaste.api

import com.example.glorytaste.data.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApiService {
    @POST("/order")
    suspend fun postOrder(@Body data: OrderRequest): Response<Void>
}

// Define the request wrapper class
data class OrderRequest(val data: Order)
