package com.example.glorytaste.data

import com.google.gson.annotations.SerializedName

data class MenuItem(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("price") val price: String,
    @SerializedName("details") val details: String
)

