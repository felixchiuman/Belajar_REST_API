package com.felix.belajarrestapi.model.Login


import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)