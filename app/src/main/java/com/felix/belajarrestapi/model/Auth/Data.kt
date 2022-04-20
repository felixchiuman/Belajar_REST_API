package com.felix.belajarrestapi.model.Auth


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("username")
    val username: String
)