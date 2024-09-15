

package com.example.glorytaste.api

import com.example.glorytaste.data.MenuItem
import retrofit2.http.GET

interface MenuApiService {
    @GET("/menu")
    suspend fun getMenuItems(): List<MenuItem>
}
