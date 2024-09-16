package com.example.glorytaste.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuItem(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("price") val price: String,
    @SerializedName("details") val details: String
) : Parcelable


//package com.example.glorytaste.data
//
//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize
//
//@Parcelize
//data class MenuItem(
//    val id: String,
//    val name: String,
//    val img: String,
//    val price: String,
//    val details: String
//) : Parcelable

